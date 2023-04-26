package pl.kurs.schooldiary.services;

import org.springframework.stereotype.Service;
import pl.kurs.schooldiary.config.PathConfig;

@Service
public class PathBuilderService {
    public final String PATH_START = PathConfig.PATH_START;
    public final String PATH_END = PathConfig.PATH_END;
    public final String FILE_NAME = PathConfig.FILE_NAME;
    public String createPath() {
        String userName = System.getProperty("user.name");
        return PATH_START + userName + PATH_END + FILE_NAME;
    }

}
