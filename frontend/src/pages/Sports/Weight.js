import '../../styles/ui.weight.css';
import '../../styles/ui.table.css';
import React, { useEffect, useState } from 'react';
import { Chart } from 'react-google-charts';
import AddWeightForm from '../Forms/AddWeightForm';

const Weight = () => {
    const [weightsGraphics, setWeightsGraphics] = useState([]);
    const [weights, setWeights] = useState([]);
    const [loading, setLoading] = useState(true);
    const [userId, setUserId] = useState(null);
    const apiUrl = process.env.REACT_APP_API_URL || "http://e-softworks.consulting:8585";

    useEffect(() => {
        setUserId(localStorage.getItem("user"));
        const userId = localStorage.getItem("user");
        
        const fetchWeightsGraphics = async () => {
            try {
                const response = await fetch(`${apiUrl}/api/weights/graphics?userId=${userId}`, {
                    method: 'GET',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                });
                if (!response.ok) {
                    throw new Error(`HTTP error! Status: ${response.status}`);
                }
                const data = await response.json();
                setWeightsGraphics(data);
            } catch (error) {
                console.error("Error fetching weights:", error);
            } finally {
                setLoading(false);
            }
        };
        
        const fetchWeights = async () => {
            try {
                const response = await fetch(`http://82.165.73.209:8585/api/weights/all?userId=${userId}`, {
                    method: 'GET',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                });
                if (!response.ok) {
                    throw new Error(`HTTP error! Status: ${response.status}`);
                }
                const data = await response.json();
                setWeights(data);
            } catch (error) {
                console.error("Error fetching weights:", error);
            } finally {
                setLoading(false);
            }
        };

        fetchWeightsGraphics();
        fetchWeights();
    }, []);

    // Prepare chart data
    const chartData = [
        ['Date', 'Weight', 'Fat'],
        ...weightsGraphics.map(weight => [weight.weeknumber, weight.weight, weight.fatkg])
    ];

    console.log('Formatted chartData:', chartData);

    return (
        <div>
            <AddWeightForm />
            {loading ? (
                <p>Loading...</p>
            ) : (
                <div className="weight-container">
                    <div className="weight-table-container">
                        <table className="weightTable">
                            <thead>
                                <tr>
                                    <th>Date</th>
                                    <th>Min Weight (kg)</th>
                                    <th>Avg Weight (kg)</th>
                                    <th>Max Weight (kg)</th>
                                    <th>Trend</th>
                                    <th>Fat %</th>
                                </tr>
                            </thead>
                            <tbody>
                                {weights.map(weight => {
                                        console.log("Weight object structure:", weight);
                                return (
                                    <tr key={weight.month}>
                                        <td>{weight.month}</td>
                                        <td>{weight.minWeight ? weight.minWeight.toFixed(2) : "0.00"}</td>
                                        <td>{weight.avgWeight ? weight.avgWeight.toFixed(2) : "0.00"}</td>
                                        <td>{weight.maxWeight ? weight.maxWeight.toFixed(2) : "0.00"}</td>
                                        <td>{weight.monthlyTrend ? weight.monthlyTrend.toFixed(2) : "0.00"}</td>
                                        <td>{weight.avgFatPercentage ? weight.avgFatPercentage.toFixed(2) : "0.00"}</td>
                                    </tr>
                                );})}
                            </tbody>
                        </table>
                    </div>
                    <div className="weight-chart">
                        <Chart
                            chartType="AreaChart"
                            width="100%"
                            height="500px"
                            data={chartData}
                            options={{
                                title: "Weight Progress",
                                hAxis: { title: "Date" },
                                vAxis: { title: "Weight (kg)" },
                                series: {
                                    0: { color: 'blue' }, // Line for 'Weight (kg)'
                                    1: { color: 'red' },  // Line for 'Fat (kg)'
                                },
                                areaOpacity: 0.2, // Opacity for the area fill
                            }}
                        />
                    </div>
                </div>
            )}
        </div>
    );
};

export default Weight;
