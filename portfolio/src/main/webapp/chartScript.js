google.charts.load('current', {'packages':['corechart']});
google.charts.setOnLoadCallback(drawChart);

function drawChart() {
  fetch('/vsVote-data').then(response => response.json())
  .then((videoVotes) => {
    const data = new google.visualization.DataTable();
    data.addColumn('string', 'Video');
    data.addColumn('number', 'Votes');
    Object.keys(videoVotes).forEach((video) => {
      data.addRow([video, videoVotes[video]]);
    });

    const options = {
      'title': 'Favorite Video Streaming Sites',
      'width':600,
      'height':500
    };

    const chart = new google.visualization.ColumnChart(
        document.getElementById('chart-container'));
    chart.draw(data, options);
  });
}