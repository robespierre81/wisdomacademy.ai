// SPDX-License-Identifier: MIT
pragma solidity ^0.8.20;

contract UserProfile {
    struct User {
        string name;
        string bio;
        string profilePhoto;  // IPFS or external storage hash
        uint256 tokens;
        uint256[] achievements;
        uint256[] badges;
        uint256[] learningPlans;
        uint256[] mentorSessions;
        uint256[] purchasedCourses;
    }

    mapping(address => User) private users;
    mapping(address => bool) private registeredUsers;

    event ProfileUpdated(address indexed user, string name, string bio, string profilePhoto);
    event TokensUpdated(address indexed user, uint256 amount);
    event BadgeAdded(address indexed user, uint256 badgeId);
    event LearningPlanAdded(address indexed user, uint256 learningPlanId);
    event MentorSessionAdded(address indexed user, uint256 mentorSessionId);
    event CourseAdded(address indexed user, uint256 courseId);

    // ✅ Register new user (first-time profile creation)
    function registerUser(string memory name, string memory bio, string memory profilePhoto) public {
        require(!registeredUsers[msg.sender], "User already registered");

        User storage u = users[msg.sender];
        u.name = name;
        u.bio = bio;
        u.profilePhoto = profilePhoto;
        registeredUsers[msg.sender] = true;

        emit ProfileUpdated(msg.sender, name, bio, profilePhoto);
    }

    // ✅ Update user profile
    function updateUser(string memory name, string memory bio, string memory profilePhoto) public {
        require(registeredUsers[msg.sender], "User not registered");

        User storage u = users[msg.sender];
        u.name = name;
        u.bio = bio;
        u.profilePhoto = profilePhoto;

        emit ProfileUpdated(msg.sender, name, bio, profilePhoto);
    }

    function updateTokens(uint256 amount) public {
        require(registeredUsers[msg.sender], "User not registered");
        users[msg.sender].tokens = amount;
        emit TokensUpdated(msg.sender, amount);
    }

    function addBadge(uint256 badgeId) public {
        require(registeredUsers[msg.sender], "User not registered");
        users[msg.sender].badges.push(badgeId);
        emit BadgeAdded(msg.sender, badgeId);
    }

    function addLearningPlan(uint256 planId) public {
        require(registeredUsers[msg.sender], "User not registered");
        users[msg.sender].learningPlans.push(planId);
        emit LearningPlanAdded(msg.sender, planId);
    }

    function addMentorSession(uint256 sessionId) public {
        require(registeredUsers[msg.sender], "User not registered");
        users[msg.sender].mentorSessions.push(sessionId);
        emit MentorSessionAdded(msg.sender, sessionId);
    }

    function addCourse(address user, uint256 courseId) external {
        users[user].purchasedCourses.push(courseId);
        emit CourseAdded(user, courseId);
    }

    function getCourses(address user) external view returns (uint256[] memory) {
        return users[user].purchasedCourses;
    }

    function getUserProfile(address user) external view returns (
        string memory,
        string memory,
        string memory,
        uint256,
        uint256[] memory,
        uint256[] memory,
        uint256[] memory,
        uint256[] memory
    ) {
        require(registeredUsers[user], "User not registered");
        User storage u = users[user];
        return (
            u.name,
            u.bio,
            u.profilePhoto,
            u.tokens,
            u.achievements,
            u.badges,
            u.learningPlans,
            u.mentorSessions
        );
    }
}
