// SPDX-License-Identifier: MIT
pragma solidity ^0.8.0;

contract UserDataStorage {
    mapping(address => string) private userData;

    function storeUserData(string memory _data) public {
        userData[msg.sender] = _data;
    }

    function retrieveUserData(address user) public view returns (string memory) {
        return userData[user];
    }
}
