package kuit.subway.domain;

import jakarta.persistence.*;
import kuit.subway.dto.SaveLineReq;
import kuit.subway.dto.UpdateLineReq;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.sql.Update;

import java.util.List;


@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Line extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String color;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Long distance;

    @Embedded
    private Sections sections = new Sections();
    public void addSection(Section section){
        this.sections.addSection(section);
        section.setLine(this);
    }

    public List<Station> getStations(){
        return this.sections.getStations();
    }
    public void removeSection() {
        sections.validateDeleteSection();
        sections.removeSection();
    }

    public void updateLine(String name, String color) {
        this.name = name;
        this.color = color;
    }
}
