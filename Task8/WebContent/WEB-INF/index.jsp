<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%@include file="header.jsp" %>
<div class="container">
	<!-- Filtering Menu
	================================================== -->
	<!-- <div class="row-fluid">
		<div class="span12 text-center">
			<div id="filter">
				<ul>
					<li><a href="" data-filter="*" class="selected"><i class="icon icon-reorder"></i> All Items</a></li>
					<li><a href="" data-filter=".cat1"><i class="icon icon-th-large"></i> Landscape</a></li>
					<li><a href="" data-filter=".cat2"><i class="icon icon-th-list"></i> Overview</a></li>
					<li><a href="" data-filter=".cat3"><i class="icon icon-th"></i> Green Nature</a></li>
				</ul>
			</div>
		</div>
	</div> -->
	<!-- END filtering menu -->
	<!-- MASONRY ITEMS START
	================================================== -->
	<div class="row">
		<div class="span10">
			<div id="content">	
				<!-- box 1 -->
				<c:forEach var="photo" items="${photoList}">
				<div class="boxportfolio4 cat2 cat3">
					<div class="boxcontainer">
						<img src="${photo.photo.urlm}" alt="">
						<div class="roll">
							<div class="wrapcaption">
								<a href="category.do?cat=${photo.category}"><i class="icon-link captionicons"></i></a>
								<a data-gal="prettyPhoto[gallery1]" href="${photo.photo.url}" title="La Chaux De Fonds"><i class="icon-zoom-in captionicons"></i></a>
							</div>
						</div>
						<h1><a href="category.do?cat=${photo.category}">${photo.catName}</a></h1>
						<p>
							 ${photo.photo.title}
						</p>
					</div>
				</div>
				</c:forEach>
			</div>
		</div>
		
		<!-- twitter stream -->
		<div class="span2">
		</div>
	</div>
<!-- MASONRY ITEMS END -->
</div>
<%@include file="footer.jsp" %>
