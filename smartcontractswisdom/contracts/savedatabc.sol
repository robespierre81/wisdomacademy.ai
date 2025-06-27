// SPDX-License-Identifier: MIT
pragma solidity ^0.8.0;

contract StoreData {
    mapping(address => string) public userData;

    event DataStored(address indexed user, string data);

    function storeUserData(string memory _data) public {
        userData[msg.sender] = _data;
        emit DataStored(msg.sender, _data);
    }

    function retrieveUserData(address user) public view returns (string memory) {
        return userData[user];
    }
}
