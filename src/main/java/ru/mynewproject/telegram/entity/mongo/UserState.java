package ru.mynewproject.telegram.entity.mongo;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Document(collation = "userSate")
@Data
public class UserState {
    @Id
    private Long userId;
    private String name;
}
