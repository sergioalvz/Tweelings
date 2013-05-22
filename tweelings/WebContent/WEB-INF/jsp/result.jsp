<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<script charset="UTF-8" type="text/javascript" src="https://www.google.com/jsapi"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/style.css">
<title>Tweelings. Monitorización sentimientos en Twitter.</title>
</head>
<body>
	<my:header />
	<section>
		<h3>
			<s:property value="%{#request.job.token}" />
		</h3>
		<h4>
			<s:property value="%{#request.job.description}" />
		</h4>
		
		<section>
		<p><s:property value="%{#request.job.observations}" /></p>
		</section>
		
		
		<div id="chart_div"></div>

		<script charset="UTF-8" type="text/javascript">
			var dataProvider = new Array();
			dataProvider.push([ "Tiempo", "Tendencia" ]);
			var line;
		</script>

		<s:iterator id="results" value="%{#request.trends}">
			<script>
				line = [ "<s:property value='date' />",
						parseFloat("<s:property value='value' />") ];
				dataProvider.push(line);
			</script>
		</s:iterator>

		<script charset="UTF-16" type="text/javascript">
			google.load("visualization", "1", {
				packages : [ "corechart" ]
			});
			google.setOnLoadCallback(drawChart);
			function drawChart() {
				var data = google.visualization.arrayToDataTable(dataProvider);

				var title = '<s:property value="%{#request.job.token}" /> : <s:property value="%{#request.job.description}" />';
				
				var options = {
					hAxis : {
						title : 'Evolución de la tendencia a lo largo del evento',
						titleTextStyle : {
							color : 'black'
						},
						slantedText : 'false',
						logScale : 'true'
					},
					vAxis : {
						maxValue : '10',
						minValue : '0'
					},					
					colors : [ '#00acee' ]
				};

				var chart = new google.visualization.AreaChart(document
						.getElementById('chart_div'));
				chart.draw(data, options);
			}
		</script>
	</section>
	<my:footer />
</body>
</html>