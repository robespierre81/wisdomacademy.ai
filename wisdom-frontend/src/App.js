
import React, { useEffect, useState } from 'react';
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import './styles/ui.general.css';
import './styles/ui.menu.css';

import Web3Auth from './components/Web3Auth';
import Web3Login from './components/Web3Login';

import UserProfile from './components/UserProfile';
import WisdomAcademyStart from './components/WisdomAcademyStart';
import HeadMenu from "./components/HeadMenu";

import Courses from "./pages/learn/Courses";
import CourseDetails from "./pages/learn/CourseDetails";
import Assessments from "./pages/learn/Assessments";
import AssessmentDetails from "./pages/learn/AssessmentDetails";
import LearningPlans from "./pages/learn/LearningPlans";
import LearningPlanDetails from "./pages/learn/LearningPlanDetails";
import Certifications from "./pages/learn/Certifications";
import Certificate from "./pages/learn/Certificate";

import FindMentor from "./pages/mentor/FindMentor";
import BecomeMentor from "./pages/mentor/BecomeMentor";
import MentorSessions from "./pages/mentor/MentorSessions";

import Marketplace from "./pages/Marketplace";
import BuyTokens from "./pages/marketplace/BuyTokens";
import EarnRewards from "./pages/marketplace/EarnRewards";
import MyTransactions from "./pages/marketplace/MyTransactions";

import Badges from "./pages/community/Badges";
import Discussion from "./pages/community/Discussion";
import Events from "./pages/community/Events";
import Leaderboard from "./pages/community/Leaderboard";

import ManageCourses from "./pages/manage/ManageCourses";
import ManageLearningPlans from "./pages/manage/ManageLearningPlans";
import ManageAssessments from "./pages/manage/ManageAssessments";
import ManageCertifications from "./pages/manage/ManageCertifications";
import ManageEvents from "./pages/manage/ManageEvents";
import ManageBadges from "./pages/manage/ManageBadges";

import { logVisitor } from './services/VisitorService';

function App() {
    const [selectedTool, setSelectedTool] = useState(null);
    const [isLoggedIn, setIsLoggedIn] = useState(false);
    const [isAllowed, setIsAllowed] = useState(false);
    const [userId, setUserId] = useState(null);

    useEffect(() => {
        document.title = 'Wisdom AI';
        logVisitor(); // Log visitor on initial load
        // Simulate a user login check (replace with real authentication check)
        const userLoggedIn = Boolean(localStorage.getItem("user")); // Example login check

        setIsLoggedIn(userLoggedIn);
        setUserId(localStorage.getItem("user"));
        
    }, []);

    const handleConnect = async (address) => {
        //setUserAddress(address); // Correctly set the address
        localStorage.setItem("user", address);
        setIsLoggedIn(true); // Set login status to true when a wallet is connected
        setIsAllowed(true);
    };

    const handleUserId = (userId) => {
        console.log('User ID from Web3Auth:', Boolean(localStorage.getItem("user")));
    };

    const handleToolSelect = (toolName) => {
        setSelectedTool(toolName);
    };

    return (
            <div className="App">
                <Web3Login onConnect={handleConnect} />
                <Web3Auth onUserId={handleUserId} />
                <Web3Auth onUserId={setUserId} />
                {isLoggedIn && isAllowed ? (
                        <>
                        <HeadMenu />
                        <p>&nbsp;</p>
                        <Routes>
                            <Route path="/" element={ < UserProfile / > } />
                            <Route path="/courses/:courseId" element={ < CourseDetails / > } />
                            <Route path="/assessments" element={ < Assessments / > } />
                            <Route path="/assessmentdetails/:assessmentId" element={ < AssessmentDetails / > } />
                            <Route path="/learningplans" element={ < LearningPlans / > } />
                            <Route path="/learningplans/:id" element={ < LearningPlanDetails / > } />
                            <Route path="/certifications" element={ < Certifications / > } />
                            <Route path="/certificates/:id" element={<Certificate />} />
                            
                            <Route path="/mentor/find" element={ < FindMentor / > } />
                            <Route path="/mentor/become" element={ < BecomeMentor / > } />
                            <Route path="/mentor/sessions" element={ < MentorSessions / > } />
                            
                            <Route path="/marketplace/buytokens" element={ < BuyTokens / > } />
                            <Route path="/marketplace/earnrewards" element={ < EarnRewards / > } />
                            <Route path="/marketplace/mytransactions" element={ < MyTransactions / > } />
                            
                            <Route path="/community/badges" element={ < Badges / > } />
                            <Route path="/community/discussions" element={ < Discussion / > } />
                            <Route path="/community/events" element={ < Events / > } />
                            <Route path="/community/leaderboard" element={ < Leaderboard / > } />

                            <Route path="/manage/courses" element={ < ManageCourses / > } />
                            <Route path="/manage/learning-plans" element={ < ManageLearningPlans / > } />
                            <Route path="/manage/assessments" element={ < ManageAssessments / > } />
                            <Route path="/manage/certifications" element={ < ManageCertifications / > } />
                            <Route path="/manage/events" element={ < ManageEvents / > } />
                            <Route path="/manage/badges" element={ < ManageBadges / > } />
                            <Route path="/profile" element={ < UserProfile / > } />
                            <Route path="/courses" element={ < Courses / > } />
                        </Routes>
                        </>
                    ) : (
                        <WisdomAcademyStart />
                    )
                }
            </div>
    );
}
export default App;
