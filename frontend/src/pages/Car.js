import React from 'react';

const Car = () => (
            <div>
                <h2>Car</h2>
                <table>
                    <tr>
                        <th onclick="startOperation('car','tourview')" id="partTouren">Tours</th>
                        <th onclick="startOperation('car','newtour')" id="partTouren">New Tour</th>
                        <th onclick="startOperation('car','yearview')" id="partJahresuebersicht">Year's Mileage</th>
                    </tr>
                </table>
                <div id="contentcontent">
                </div>
                {/* Tool-specific content goes here */}
            
            </div>
            );

export default Car;
