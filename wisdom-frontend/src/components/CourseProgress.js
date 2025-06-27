import { useState, useEffect } from "react";
import { BrowserProvider, Contract } from "ethers";

const useCourseProgress = (walletAddress, courseId) => {
    const [progress, setProgress] = useState(0);

    useEffect(() => {
        const fetchProgress = async () => {
    try {
        const provider = new BrowserProvider(window.ethereum);
        const signer = await provider.getSigner(); // Required in v6
        const contractAddress = localStorage.getItem("user");
        const abi = [
            "function getProgress(uint courseId) public view returns (uint)"
        ];
        const contract = new Contract(contractAddress, abi, signer);
        const progressValue = await contract.getProgress(courseId);
        setProgress(progressValue);
    } catch (error) {
        console.error("Error fetching progress:", error);
    }
};

        if (walletAddress && courseId) {
            fetchProgress();
        }
    }, [walletAddress, courseId]);

    return progress;
};

export default useCourseProgress;
