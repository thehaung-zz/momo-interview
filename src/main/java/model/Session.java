package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author : Hau Nguyen
 * @created : 6/1/22
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Session {
    private String id = UUID.randomUUID().toString();

    private Date createdDate = new Date();

    private List<Order> orders;

    private Boolean isEnded = false;
}
