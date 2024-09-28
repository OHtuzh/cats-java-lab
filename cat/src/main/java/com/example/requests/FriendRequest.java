package com.example.requests;

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