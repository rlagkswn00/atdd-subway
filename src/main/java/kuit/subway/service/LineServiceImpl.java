package kuit.subway.service;

import kuit.global.BaseResponseStatus;
import kuit.global.exception.SubwayException;
import kuit.subway.domain.Line;
import kuit.subway.domain.Station;
import kuit.subway.dto.*;
import kuit.subway.repository.LineRepository;
import kuit.subway.repository.StationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.sql.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static kuit.global.BaseResponseStatus.*;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class LineServiceImpl implements LineService {

    private final LineRepository lineRepository;
    private final StationRepository stationRepository;

    @Override
    public SaveLineRes createLines(SaveLineReq saveLineReq) {
        validateUpstationAndDownStation(saveLineReq.getUpStationId(), saveLineReq.getDownStationId());

        if (lineRepository.existsLineByName(saveLineReq.getName()))
            throw new SubwayException(DUPLICATE_LINE);
        Station upStation = stationRepository.findById(saveLineReq.getUpStationId()).get();
        Station downStation = stationRepository.findById(saveLineReq.getDownStationId()).get();

        Line line = Line.builder()
                .name(saveLineReq.getName())
                .color(saveLineReq.getColor())
                .downStation(downStation)
                .upStation(upStation)
                .distance(saveLineReq.getDistance())
                .build();

        Long id = lineRepository.save(line).getId();
        return new SaveLineRes(id);
    }

    @Override
    @Transactional(readOnly = true)
    public FindLinesRes findLines(Long id) {
        if (!lineRepository.existsById(id))
            throw new SubwayException(NOT_EXIST_LINE);

        Line line = lineRepository.findById(id).get();

        List<FindStationsRes> stationList = getStationInfoList(line);

        return FindLinesRes.builder()
                .id(id)
                .stations(stationList)
                .name(line.getName())
                .color(line.getColor())
                .createdDate(line.getCreatedDate())
                .modifiedDate(line.getModifiedDate())
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public List<FindLinesRes> findAllLines() {
        List<FindLinesRes> findLinesResList = new ArrayList<>();
        List<Line> allLines = lineRepository.findAll();
        for (Line line : allLines) {
            List<FindStationsRes> stationInfoList = getStationInfoList(line);
            findLinesResList.add(FindLinesRes.builder()
                    .id(line.getId())
                    .stations(stationInfoList)
                    .name(line.getName())
                    .color(line.getColor())
                    .createdDate(line.getCreatedDate())
                    .modifiedDate(line.getModifiedDate())
                    .build());
        }

        return findLinesResList;
    }

    @Override
    public Long deleteLine(Long id) {
        if (!lineRepository.existsById(id))
            throw new SubwayException(NOT_EXIST_LINE);

        lineRepository.deleteById(id);
        return id;
    }

    @Override
    public Long updateLine(Long id, UpdateLineReq updateLineReq) {
        validateUpstationAndDownStation(updateLineReq.getUpStationId(), updateLineReq.getDownStationId());

        if (!lineRepository.existsById(id))
            throw new SubwayException(NOT_EXIST_LINE);

        Line line = lineRepository.findById(id).get();

        Station upStation = stationRepository.findById(updateLineReq.getUpStationId()).get();
        Station downStation = stationRepository.findById(updateLineReq.getDownStationId()).get();

        line.updateLine(updateLineReq, upStation, downStation);

        return id;
    }

    private List<FindStationsRes> getStationInfoList(Line line) {
        Station upStation = line.getUpStation();
        Station downStation = line.getDownStation();

        List<FindStationsRes> stations = new ArrayList<>();
        stations.add(new FindStationsRes(upStation.getId(), upStation.getName()));
        stations.add(new FindStationsRes(downStation.getId(), downStation.getName()));

        return stations ;
    }
    private void validateUpstationAndDownStation(Long upStationId, Long downStationId){
        if(!stationRepository.existsById(upStationId) || !stationRepository.existsById(downStationId))
            throw new SubwayException(NOT_EXIST_STATION);

        if(upStationId.equals(downStationId))
            throw new SubwayException(SAME_UP_DOWN_STATION);
    }
}
