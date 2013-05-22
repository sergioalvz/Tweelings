<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min.js"></script>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/jquery-ui.min.js"></script>
<script type="text/javascript" src="js/script.js"></script>
<link rel="stylesheet" type="text/css" href="css/style.css">
<title>Tweelings. Monitorización sentimientos en Twitter.</title>
</head>
<body>
	<my:header />
	<section class="presentation">
		<h3>Tweelings</h3>
		<p>
			<strong>Tweelings</strong> es una aplicación web que muestra al usuario la tendencia en <strong>Twitter</strong> de
			ciertos términos asociados a deportistas de élite durante la realización de eventos deportivos.
		</p>

		<p>El objetivo principal de Tweelings es demostrar como el análisis de las opiniones vertidas en Twitter durante
			la proyección de un evento deportivo, pueden ayudar a obtener información acerca del resultado de los mismos,
			determinando si la participación de un deportista en concreto ha sido o no positiva, o si han existido picos donde su
			actividad ha tenido una mayor repercursión</p>

	</section>
	<section id="results">
		<h3>Fórmula 1</h3>
		<div>
			<ul>
				<s:iterator id="jobsF1" value="%{#application.jobsF1}">
					<li><s:url id="jobF1" action="ShowResults">
							<s:param name="jobId" value="id" />
						</s:url> <s:a href="%{jobF1}">
							<s:property value="token" /> — <s:property value="description" />
						</s:a></li>
				</s:iterator>
			</ul>
		</div>
		<h3>Fútbol</h3>
		<div>
			<ul>
				<s:iterator id="jobsFootball" value="%{#application.jobsFootball}">
					<li><s:url id="jobFootball" action="ShowResults">
							<s:param name="jobId" value="id" />
						</s:url> <s:a href="%{jobFootball}">
							<s:property value="token" /> — 
							<s:property value="description" />
						</s:a></li>
				</s:iterator>
			</ul>
		</div>
		<h3>Tenis</h3>
		<div>
			<ul>
				<s:iterator id="jobsTennis" value="%{#application.jobsTennis}">
					<li><s:url id="jobTennis" action="ShowResults">
							<s:param name="jobId" value="id" />
						</s:url> <s:a href="%{jobTennis}">
							<s:property value="token" /> — 
							<s:property value="description" />
						</s:a></li>
				</s:iterator>
			</ul>
		</div>
	</section>
	<my:footer />
</body>
</html>