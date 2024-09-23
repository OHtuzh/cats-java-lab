package org.example.services;

public interface CommonService extends CatService, OwnerService, CatCheckUserService {
    void addFriendCheckUser(Integer leftCatUuid, Integer rightCatUuid);
}
