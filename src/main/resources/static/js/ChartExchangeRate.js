$(document).ready(function () {
    $.ajax({
        url: '/Dashboard/Chart',
        type: 'GET',
        dataType: 'json',
        success: function (data) {
            var exchangeKeys = data.exchangeKey;
            var exchangeValues = data.exchangeValue;

            var ctx = document.getElementById('exchangeChart').getContext('2d');
            var myChart = new Chart(ctx, {
                type: 'bar',
                data: {
                    labels: exchangeKeys,
                    datasets: [{
                        label: 'Döviz Kurları (Baz: USD)',
                        data: exchangeValues,
                        backgroundColor: [
                            'rgba(54, 162, 235, 0.6)',
                            'rgba(75, 192, 192, 0.6)',
                            'rgba(255, 206, 86, 0.6)',
                            'rgba(153, 102, 255, 0.6)',
                            'rgba(255, 159, 64, 0.6)'
                        ],
                        borderColor: [
                            'rgba(54, 162, 235, 1)',
                            'rgba(75, 192, 192, 1)',
                            'rgba(255, 206, 86, 1)',
                            'rgba(153, 102, 255, 1)',
                            'rgba(255, 159, 64, 1)'
                        ],
                        borderWidth: 1
                    }]
                },
                options: {
                    responsive: true,
                    scales: {
                        yAxes: [{
                            ticks: {
                                beginAtZero: true
                            }
                        }]
                    }
                }
            });
        },
        error: function (xhr, status, error) {
            console.error('Veri yüklenirken hata oluştu:', status, error);
        }
    });
});