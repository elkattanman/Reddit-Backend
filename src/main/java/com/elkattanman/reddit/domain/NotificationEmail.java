package com.elkattanman.reddit.domain;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
public class NotificationEmail implements Serializable {
    private String subject;
    private String recipient;
    private String body;
}
