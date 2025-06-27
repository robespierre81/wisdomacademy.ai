import './styles/ui.general.css';
import React, { useEffect, useState } from 'react';
import Web3Auth from './components/Web3Auth';
import Web3Login from './components/Web3Login';
import NavigationPane from './components/NavigationPane';
import Electricity from './pages/Electricity';
import Car from './pages/Car';
import Financials from './pages/Financials';
import Sports from './pages/Sports';
import ConsultingStart from './pages/ConsultingStart';
import HeadMenu from './pages/HeadMenu';

function App() {
    const [selectedTool, setSelectedTool] = useState(null);
    const [isLoggedIn, setIsLoggedIn] = useState(false);
    const [isAllowed, setIsAllowed] = useState(false);
    const [userId, setUserId] = useState(null);

    useEffect(() => {
        document.title = 'Wisdom Learning AI';
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

    const renderToolContent = () => {
        if (!isAllowed) {
            return <ConsultingStart message="You do not have access to the selected tools." />;
        }

        switch (selectedTool) {
            case 'electricity':
                return <Electricity />;
            case 'car':
                return <Car />;
            case 'financial':
                return <Financials />;
            case 'sports':
                return <Sports />;
            default:
                return <p>Select a tool from the navigation pane.</p>;
        }
    };

    return (
        <div className="App">
       <HeadMenu></HeadMenu>
            <Web3Login onConnect={handleConnect} />
            <Web3Auth onUserId={handleUserId} />
            <Web3Auth onUserId={setUserId} />

            {isLoggedIn && isAllowed ? (
                <>
                    <div id="myH1"><h1>e-Softworks Consulting</h1></div>
                    <NavigationPane isLoggedIn={isLoggedIn} onToolSelect={handleToolSelect} />
                    <div id="contentPane">
                        {renderToolContent()}
                    </div>
                </>
            ) : (
                <ConsultingStart message="Please connect your wallet to get started." />
            )}
        </div>
    );
}

export default App;
