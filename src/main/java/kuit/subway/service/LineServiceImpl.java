package kuit.subway.service;

import kuit.global.BaseResponseStatus;
import kuit.global.exception.SubwayException;
import kuit.subway.domain.Line;
import kuit.subway.domain.Section;
import kuit.subway.domain.Sections;
import kuit.subway.domain.Station;
import kuit.subway.dto.*;
import kuit.subway.repository.LineRepository;
import kuit.subway.repository.StationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static kuit.global.BaseResponseStatus.*;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class LineServiceImpl implements LineService {

    private final LineRepository lineRepository;
    private final StationRepository stationRepository;

    @Override
    public Long createLines(SaveLineReq saveLineReq) {
        validateUpstationAndDownStation(saveLineReq.getUpStationId(), saveLineReq.getDownStationId());

        if (lineRepository.existsLineByName(saveLineReq.getName()))
            throw new SubwayException(DUPLICATE_LINE);

        Station upStation = stationRepository.findById(saveLineReq.getUpStationId()).get();
        Station downStation = stationRepository.findById(saveLineReq.getDownStationId()).get();

        Line line = Line.builder()
                .name(saveLineReq.getName())
                .distance(saveLineReq.getDistance())
                .color(saveLineReq.getColor())
                .sections(new Sections()) // 빌더 혐오 시작;;
                .build();

        Section section = Section.builder()
                .upStation(upStation)
                .downStation(downStation)
                .build();

        line.addSection(section);
        Long id = lineRepository.save(line).getId();

        return id;
    }

    @Override
    @Transactional(readOnly = true)
    public FindLinesRes findLine(Long id) {
        validateLine(id);
        Line line = lineRepository.findById(id).get();
        List<FindStationsRes> stations = getStationInfoList(line);

        return FindLinesRes.of(line, stations);
    }

    @Override
    @Transactional(readOnly = true)
    public List<FindLinesRes> findAllLine() {
        List<FindLinesRes> findLinesResList = new ArrayList<>();
        List<Line> allLines = lineRepository.findAll();
        for (Line line : allLines) {
            List<FindStationsRes> stations = getStationInfoList(line);
            findLinesResList.add(FindLinesRes.of(line, stations));
        }

        return findLinesResList;
    }

    @Override
    public Long deleteLine(Long id) {
        validateLine(id);

        lineRepository.deleteById(id);
        return id;
    }

    @Override
    public Long updateLine(Long id, UpdateLineReq updateLineReq) {
        validateLine(id);

        Line line = lineRepository.findById(id).get();

        line.updateLine(updateLineReq.getColor(), updateLineReq.getName());
        return id;
    }

    @Override
    public Long createSections(SaveSectionReq saveSectionReq) {
        validateCreateSection(saveSectionReq);

        Line line = lineRepository.findById(saveSectionReq.getLineId()).get();
        Station upStation = stationRepository.findById(saveSectionReq.getUpStationId()).get();
        Station downStation = stationRepository.findById(saveSectionReq.getDownStationId()).get();

        Section section = Section.builder()
                .upStation(upStation)
                .downStation(downStation)
                .build();

        line.addSection(section);

        return line.getId();
    }

    @Override
    public Long deleteSection(Long lineId) {
        Line line = lineRepository.findById(lineId).get();
        line.removeSection();
        return lineId;
    }

    private List<FindStationsRes> getStationInfoList(Line line) {
        List<Station> stations = line.getStations();
        List<FindStationsRes> resultStations = stations.stream()
                .map(station -> FindStationsRes.from(station))
                .toList();
        return resultStations;
    }

    private void validateCreateSection(SaveSectionReq saveSectionReq) {
        //등록하고자 하는 구간내 상행역 != 하행역
        validateUpstationAndDownStation(saveSectionReq.getUpStationId(), saveSectionReq.getDownStationId());

        //라인이 존재하는지
        validateLine(saveSectionReq.getLineId());

        Line line = lineRepository.findById(saveSectionReq.getLineId()).get();

        List<Station> stations = line.getStations();
        //추가하고자 하는 구간의 하행 역이 존재하지 않는지
        Station downStation = stationRepository.findById(saveSectionReq.getDownStationId()).get();
        if (stations.contains(downStation))
            throw new SubwayException(DUPLICATE_STATION);

        //마지막 역과 추가하고자 하는 구간의 상행 역이 같은지
        Station lastStation = stations.get(stations.size() - 1);
        if (lastStation.getId() != saveSectionReq.getUpStationId())
            throw new SubwayException(INVALID_UPSTATION_SECTION);
    }

    private void validateLine(Long lineId) {
        if (!lineRepository.existsById(lineId))
            throw new SubwayException(NOT_EXIST_LINE);
    }

    private void validateUpstationAndDownStation(Long upStationId, Long downStationId) {
        if (!stationRepository.existsById(upStationId) || !stationRepository.existsById(downStationId))
            throw new SubwayException(NOT_EXIST_STATION);

        if (upStationId.equals(downStationId))
            throw new SubwayException(SAME_UP_DOWN_STATION);
    }
}
