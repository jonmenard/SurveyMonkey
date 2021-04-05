import { Chart, HistogramSeries} from '@syncfusion/ej2-charts';
Chart.Inject(HiloOpenCloseSeries);
let chartData: Object[] = [];
let points: number[] = dataSet;
points.map((value: number) => {
    chartData.push({
        y: value
    })});

let chart: Chart = new Chart({
    series:[{
        type: 'Histogram',
        dataSource: chartData,
        yName: 'y',
    }],
}, '#Chart');