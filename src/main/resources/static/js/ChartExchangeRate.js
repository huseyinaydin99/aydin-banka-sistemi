$(document).ready(function () {
    console.log("ChartExchangeRate.js loaded.");
    var chartCanvas = document.getElementById('exchangeChart');
    if (!chartCanvas) {
        console.error("Canvas element 'exchangeChart' not found!");
        return;
    }

    $.ajax({
        url: '/Dashboard/Chart',
        type: 'GET',
        dataType: 'json',
        success: function (data) {
            console.log("Chart data received:", data);
            var exchangeKeys = data.exchangeKey;
            var exchangeValues = data.exchangeValue;

            if (!exchangeKeys || !exchangeValues || exchangeKeys.length === 0) {
                console.error("Chart data is empty or invalid!");
                return;
            }

            // Chart.js 2.x için renk dizisi oluştur
            var backgroundColors = [
                'rgba(54, 162, 235, 0.6)',
                'rgba(75, 192, 192, 0.6)',
                'rgba(255, 206, 86, 0.6)',
                'rgba(153, 102, 255, 0.6)',
                'rgba(255, 159, 64, 0.6)',
                'rgba(255, 99, 132, 0.6)',
                'rgba(201, 203, 207, 0.6)'
            ];
            var borderColors = [
                'rgba(54, 162, 235, 1)',
                'rgba(75, 192, 192, 1)',
                'rgba(255, 206, 86, 1)',
                'rgba(153, 102, 255, 1)',
                'rgba(255, 159, 64, 1)',
                'rgba(255, 99, 132, 1)',
                'rgba(201, 203, 207, 1)'
            ];

            var ctx = document.getElementById('exchangeChart').getContext('2d');
            var myChart = new Chart(ctx, {
                type: 'pie',
                data: {
                    labels: exchangeKeys,
                    datasets: [{
                        label: 'Döviz Kurları (Baz: USD)',
                        data: exchangeValues,
                        backgroundColor: backgroundColors.slice(0, exchangeKeys.length),
                        borderColor: borderColors.slice(0, exchangeKeys.length),
                        borderWidth: 1
                    }]
                },
                options: {
                    responsive: true,
                    maintainAspectRatio: false,
                    legend: {
                        position: 'right'
                    }
                }
            });
        },
        error: function (xhr, status, error) {
            console.error('Veri yüklenirken hata oluştu:', status, error);
        }
    });
});