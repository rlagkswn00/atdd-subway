package kuit.subway.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.OneToMany;
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

    public void addSection(Section section) {
        log.info(section.toString());
        this.sections.add(section);
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