import React from 'react';

const Financial = () => (
  <div>
      <h2>Financials</h2>
      <table>
    <tr>
        <th onclick="startOperation('financial','posting')" id="posting">Posting</th>
        <th onclick="startOperation('financial','credits')" id="credits">Credits</th>
        <th onclick="startOperation('financial','ip')" id="ip">IP</th>
        <th onclick="startOperation('financial','food')" id="fiid">Food/Month</th>
        <th onclick="startOperation('financial','monthview')" id="monthview">Monthly</th>
        <th onclick="startOperation('financial','quarterview')" id="quarterview">Quarters</th>
        <th onclick="startOperation('financial','yearview')" id="yearview">Years</th>
        <th onclick="startOperation('financial','yeargraph')" id="yeargraph">Y-Graph</th>
        <th onclick="startOperation('financial','quartergraph')" id="quartergraph">Q-Graph</th>
    </tr>
</table>
    {/* Tool-specific content goes here */}
  </div>
);

export default Financial;
