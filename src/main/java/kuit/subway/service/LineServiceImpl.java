package kuit.subway.service;

import jakarta.transaction.Transactional;
import kuit.global.BaseResponseStatus;
import kuit.global.exception.SubwayException;
import kuit.subway.domain.Line;
import kuit.subway.domain.Station;
import kuit.subway.dto.FindLinesRes;
import kuit.subway.dto.FindStationsRes;
import kuit.subway.dto.SaveLineReq;
import kuit.subway.dto.SaveLineRes;
import kuit.subway.repository.LineRepository;
import kuit.subway.repository.StationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class LineServiceImpl implements LineService{

    private final LineRepository lineRepository;
    private final StationRepository stationRepository;

    @Override
    public SaveLineRes createLines(SaveLineReq saveLineReq) {
        if(lineRepository.existsLineByName(saveLineReq.getName()))
            throw new SubwayException(BaseResponseStatus.DUPLICATE_LINE);

        Line line = Line.builder()
                .name(saveLineReq.getName())
                .color(saveLineReq.getColor())
                .downStationId(saveLineReq.getDownStationId())
                .upStationId(saveLineReq.getUpStationId())
                .distance(saveLineReq.getDistance())
                .build();

        Long id = lineRepository.save(line).getId();
        return new SaveLineRes(id);
    }

    @Override
    public FindLinesRes findLines(Long id) {
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

    private List<FindStationsRes> getStationInfoList(Line line){
        Long upStationId = line.getUpStationId();
        Station upStation = stationRepository.findById(upStationId).get();

        Long downStationId = line.getDownStationId();
        Station downStation = stationRepository.findById(downStationId).get();

        List<FindStationsRes> stationList = new ArrayList<>();
        stationList.add(new FindStationsRes(upStationId, upStation.getName()));
        stationList.add(new FindStationsRes(downStationId, downStation.getName()));

        return stationList;
    }
}
