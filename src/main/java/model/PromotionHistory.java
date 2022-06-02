package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

/**
 * @author : Hau Nguyen
 * @created : 6/1/22
 **/

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PromotionHistory {
    private String id = UUID.randomUUID().toString();

    private Session session;

    private Date createdDate = new Date();
}
