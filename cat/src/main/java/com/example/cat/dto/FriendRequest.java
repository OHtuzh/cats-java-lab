package com.example.cat.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FriendRequest {
    private Integer catUuid;
    private Integer catFriendUuid;

}