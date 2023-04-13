package pl.kurs.schooldiary.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class StatusDto {
    private String status;

    public StatusDto(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
