package kuit.subway.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.OneToMany;
import kuit.global.BaseResponseStatus;
import kuit.global.exception.SubwayException;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Embeddable
@Slf4j
@ToString
public class Sections {

    @OneToMany(mappedBy = "line", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Section> sections = new ArrayList<>();

    public void validateDeleteSection(){
        if(sections.size() == 1)
            throw new SubwayException(BaseResponseStatus.ONLY_ONE_SECTION);
    }
    public void addSection(Section section) {
        log.info(section.toString());
        this.sections.add(section);
    }

    public void removeSection() {
        this.sections.remove(sections.size()-1);
    }

    public List<Station> getStations() {
        List<Station> stations = new ArrayList<>();
        boolean isFirst = true;
        for (Section section : sections) {
            if (isFirst)
                stations.add(section.getUpStation());

            stations.add(section.getDownStation());
            isFirst = false;
        }
        return stations;
    }
}