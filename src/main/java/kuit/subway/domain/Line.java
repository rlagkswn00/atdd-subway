package kuit.subway.domain;

import jakarta.persistence.*;
import kuit.subway.dto.SaveLineReq;
import kuit.subway.dto.UpdateLineReq;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.sql.Update;


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

//    @Column(nullable = false)
//    private Long distance;

    @Embedded
    private Sections sections = new Sections();
    public void addSection(Section section){
        this.sections.addSection(section);
    }

    public void removeSection() {
        sections.removeSection();
    }

    public void updateLine(UpdateLineReq updateLineReq) {
        this.name = updateLineReq.getName();
        this.color = updateLineReq.getColor();
    }
}
