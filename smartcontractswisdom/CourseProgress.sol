// SPDX-License-Identifier: MIT
pragma solidity ^0.8.0;

contract CourseProgress {
    struct Progress {
        uint courseId;
        address user;
        uint progress;
    }

    mapping(address => mapping(uint => uint)) private userProgress; // user => (courseId => progress)

    event ProgressUpdated(address indexed user, uint indexed courseId, uint progress);

    function updateProgress(uint courseId, uint progress) public {
        require(progress >= 0 && progress <= 100, "Progress must be between 0 and 100");
        userProgress[msg.sender][courseId] = progress;
        emit ProgressUpdated(msg.sender, courseId, progress);
    }

    function getProgress(uint courseId) public view returns (uint) {
        return userProgress[msg.sender][courseId];
    }
}
