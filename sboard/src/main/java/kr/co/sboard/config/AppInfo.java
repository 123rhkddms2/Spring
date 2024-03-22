package kr.co.sboard.config;

import lombok.*;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AppInfo {

    private String name;
    private String version;


}