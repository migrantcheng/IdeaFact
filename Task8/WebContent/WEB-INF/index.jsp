<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="content-type" content="text/html; charset=UTF-8">
        <meta charset="utf-8">
        <title>Parallax707</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">
        <link href='http://fonts.googleapis.com/css?family=Kelly+Slab' rel='stylesheet' type='text/css'>
        <link href="css/reset.css" rel="stylesheet">
        <link href="css/parallax_style_1.1.3.css" rel="stylesheet" />
        <link href="css/bootstrap.css" rel="stylesheet">
        <link href="css/style.css" rel="stylesheet" />
        <link href="css/services_slider/lean-slider.css" rel="stylesheet" />
        <link rel="stylesheet" type="text/css" href="css/grid.css" />
        <!--grid slidShow -->
        <link rel="stylesheet" type="text/css" href="css/portfolio_gallery.css">
        <!--Portfolio Gallery -->
        <link href="css/font-awesome.css" rel="stylesheet">
        <!--font-awesome for Icons -->
        <link href="css/jquery.classybox.css" rel="stylesheet" />
        <!--classybox LightBox-->
        <link href="css/elastislide.css" rel="stylesheet"  />
        <!-- Gallery Section-->

        <!-- Gallery Section-->
        <script id="img-wrapper-tmpl" type="text/x-jquery-tmpl">    
            <div class="rg-image-wrapper">
                {{if itemsCount > 1}}
                    <div class="rg-image-nav">
                        <a href="#" class="rg-image-nav-prev">Previous Image</a>
                        <a href="#" class="rg-image-nav-next">Next Image</a>
                    </div>
                {{/if}}
                <div class="rg-image"></div>
                <div class="rg-loading"></div>
                <div class="rg-caption-wrapper">
                    <div class="rg-caption" style="display:none;">
                        <p></p>
                    </div>
                </div>
            </div>
        </script>

        <!--grid slidShow -->
        <script id="previewTmpl" type="text/x-jquery-tmpl">
            <div id="ib-img-preview" class="ib-preview">
                <img src="\${src}" alt="" class="ib-preview-img"/>
                <span class="ib-preview-descr" style="display:none;">\${description}</span>
                <div class="ib-nav" style="display:none;">
                    <span class="ib-nav-prev">Previous</span>
                    <span class="ib-nav-next">Next</span>
                </div>
                <span class="ib-close" style="display:none;">Close Preview</span>
                <div class="ib-loading-large" style="display:none;">Loading...</div>
            </div>      
        </script>
        <script id="contentTmpl" type="text/x-jquery-tmpl"> 
            <div id="ib-content-preview" class="ib-content-preview">
                <div class="ib-teaser" style="display:none;">{{html teaser}}</div>
                <div class="ib-content-full" style="display:none;">{{html content}}</div>
                <span class="ib-close" style="display:none;">Close Preview</span>
            </div>
        </script>

        <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
        <!--[if lt IE 9]>
            <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->
        <!--[if IE 7]>
          <link rel="stylesheet" href="assets/css/font-awesome-ie7.min.css">
          <![endif]-->
        <!-- Fav and touch icons -->
        <link rel="apple-touch-icon-precomposed" sizes="144x144" href="icon/apple-touch-icon-144-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="114x114" href="icon/apple-touch-icon-114-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="72x72" href="icon/apple-touch-icon-72-precomposed.png">
        <link rel="apple-touch-icon-precomposed" href="icon/apple-touch-icon-57-precomposed.png">
        <link rel="shortcut icon" href="icon/favicon.ico">
        </head>

        <body class="clearfix" data-spy="scroll" data-target="#navbar" data-offset="10">
<div class="container-fluid clearfix">
            <div class="row-fluid">
        <div id="welcome">
                    <div class="container-fluid clearfix" style="padding:0; overflow:hidden">
                <div id="ib-main-wrapper" class="ib-main-wrapper">
                            <div class="ib-main"> <a href="#" class="ib-content">
                            <div class="ib-teaser">
                                <h2>Welcome <span>IdeaFact</span></h2>
                            </div>
                            <div class="ib-content-full">
                                <p>Welcome to IdeaFact</p>
                            </div>
                        </a>
                        
                        
                        <c:forEach var="photo" items="${photos}">
                        <a href="#"><img src="${photo.urlm}" width="210px" height="210px" data-largesrc="${photo.url}"/><span>${photo.title}</span></a>
                        </c:forEach>
                        <div class="clr"></div>
                    </div>
                            <!-- ib-main --> 
                        </div>
                <!-- ib-main-wrapper --> 
            </div>
                </div>
        <div id="navbar" class="navbar navbar-static-top">
                    <div class="navbar-inner">
                <div class="container"> <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse"> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> </a> <a class="brand" href="index.html">Parallax<span>707</span> </a>
                            <div class="nav-collapse collapse pull-right">
                        <ul class="nav">
                                    <li class="active dropdown"> <a href="#" class="dropdown-toggle" data-toggle="dropdown">Home <b class="caret"></b></a>
                                <ul class="dropdown-menu">
                                            <li> <a href="#welcome">Home Style 1</a> </li>
                                            <li> <a href="3d_slider.html">Home Style 2</a> </li>
                                            <li> <a href="layer_slider.html">Home Style 3</a> </li>
                                            <li> <a href="nivo_slider.html">Home Style 4</a> </li>
                                            <li> <a href="parallax_slider.html">Home Style 5</a> </li>
                                            <li> <a href="portfolio_slider.html">Home Style 6</a> </li>
                                        </ul>
                            </li>
                                    <li> <a href="#About">About</a> </li>
                                    <li> <a href="#Services">Services</a> </li>
                                    <li> <a href="#Portfolio">Portfolio</a> </li>
                                    <li> <a href="#LargeSlider">Gallery</a> </li>
                                    <li> <a href="#Contact">Contact</a> </li>
                                    <li class="dropdown"> <a href="#" class="dropdown-toggle" data-toggle="dropdown"> External Pages <b class="caret"></b></a>
                                <ul class="dropdown-menu">
                                            <li><a href="about_us.html">About Us</a></li>
                                            <li><a href="our_services.html">Our Services</a></li>
                                            <li><a href="portfolio_page.html">Our Portfolio</a></li>
                                            <li><a href="gallery.html">Gallery</a></li>
                                            <li><a href="pricing.html">Pricing Tables</a></li>
                                            <li><a href="icons.html">Icons</a></li>
                                            <li><a href="time_line.html">Timeline</a></li>
                                        </ul>
                            </li>
                                </ul>
                    </div>
                            <!--/.nav-collapse --> 
                        </div>
            </div>
                </div>
        <div id="About">
                    <div class="container-fluid clearfix About">
                <div class="container clearfix">
                            <div class="container clearfix TitleSection">
                        <header class="page-head">
                                    <h1>About Us <small>// AND WHAT MAKES IT UNIQUE</small></h1>
                                </header>
                    </div>
                            <div class="container clearfix">
                        <div class="span12">
                                    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean nibh erat,
                                sagittis sit amet congue at, aliquam eu libero. Integer molestie, turpis
                                vel ultrices facilisis, nisi mauris sollicitudin mauris. aliquam eu libero. Integer molestie, turpis
                                vel ultrices facilisis, nisi mauris sollicitudin mauris.</p>
                                </div>
                        <div class="span12">
                                    <div class="span4">
                                <div class="Team clearfix">
                                            <div class="img-thumb-container">
                                        <div class="img-thumb"> <span><img src="images/about_photos/1.jpg" alt="About photos"></span>
                                                    <div style="display: none;" class="overlay-wrp">
                                                <div class="overlay"></div>
                                                <ul style="top: 36px;" class="social-icons overlay-content">
                                                            <li><a href="#"><i class="icon-facebook"></i></a></li>
                                                            <li><a href="#"><i class="icon-twitter"></i></a></li>
                                                            <li><a href="#"><i class="icon-linkedin"></i></a></li>
                                                            <li><a href="#"><i class="icon-pinterest"></i></a></li>
                                                        </ul>
                                            </div>
                                                </div>
                                    </div>
                                            <h2>John Doe</h2>
                                            <h3>Creative Director</h3>
                                            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean nibh erat,
                                        sagittis sit amet congue at, aliquam eu libero. Integer molestie, turpis
                                        vel ultrices facilisis, nisi mauris sollicitudin mauris.</p>
                                        </div>
                            </div>
                                    <div class="span4">
                                <div class="Team clearfix">
                                            <div class="img-thumb-container">
                                        <div class="img-thumb"> <span><img src="images/about_photos/2.jpg" alt="about photos"></span>
                                                    <div style="display: none;" class="overlay-wrp">
                                                <div class="overlay"></div>
                                                <ul style="top: 36px;" class="social-icons overlay-content">
                                                            <li><a href="#"><i class="icon-facebook"></i></a></li>
                                                            <li><a href="#"><i class="icon-twitter"></i></a></li>
                                                            <li><a href="#"><i class="icon-linkedin"></i></a></li>
                                                            <li><a href="#"><i class="icon-pinterest"></i></a></li>
                                                        </ul>
                                            </div>
                                                </div>
                                    </div>
                                            <h2>John Doe</h2>
                                            <h3>Art-Director</h3>
                                            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean nibh erat,
                                        sagittis sit amet congue at, aliquam eu libero. Integer molestie, turpis
                                        vel ultrices facilisis, nisi mauris sollicitudin mauris.</p>
                                        </div>
                            </div>
                                    <div class="span4">
                                <div class="Team clearfix">
                                            <div class="img-thumb-container">
                                        <div class="img-thumb"> <span><img src="images/about_photos/3.jpg" alt="About photos"></span>
                                                    <div style="display: none;" class="overlay-wrp">
                                                <div class="overlay"></div>
                                                <ul style="top: 36px;" class="social-icons overlay-content">
                                                            <li><a href="#"><i class="icon-facebook"></i></a></li>
                                                            <li><a href="#"><i class="icon-twitter"></i></a></li>
                                                            <li><a href="#"><i class="icon-linkedin"></i></a></li>
                                                            <li><a href="#"><i class="icon-pinterest"></i></a></li>
                                                        </ul>
                                            </div>
                                                </div>
                                    </div>
                                            <h2>John Doe</h2>
                                            <h3>Chief Executive Officer</h3>
                                            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean nibh erat,
                                        sagittis sit amet congue at, aliquam eu libero. Integer molestie, turpis
                                        vel ultrices facilisis, nisi mauris sollicitudin mauris.</p>
                                        </div>
                            </div>
                                </div>
                    </div>
                        </div>
            </div>
                </div>
        <!--#About-->
        <div id="intro" class="Parallax">
                    <div class="ParallaxText">
                <h2>WORD <span>OF</span> THE <span>WISE</span></h2>
                <div class="clearfix"></div>
                <blockquote>A man must be big enough to admit his mistakes, smart enough to profit
                            from them, and strong enough to correct them.</blockquote>
                <div class="clearfix"></div>
                <p>- Jason Adams -</p>
            </div>
                </div>
        <!--#intro-->
        <div id="Services">
                    <div class="container-fluid clearfix Services">
                <div class="container clearfix TitleSection">
                            <header class="page-head">
                        <h1>Our Services <small>// What We Do</small></h1>
                    </header>
                        </div>
                <div class="container clearfix">
                            <div class="span12">
                        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean nibh erat,
                                    sagittis sit amet congue at, aliquam eu libero. Integer molestie, turpis
                                    vel ultrices facilisis, nisi mauris sollicitudin mauris.</p>
                    </div>
                            <div class="row-fluid">
                        <ul id="sti-menu" class="sti-menu clearfix">
                                    <li class="span3 clearfix" data-hovercolor="#49110D"> <a href="#">
                                        <h2 data-type="mText" class="sti-item">Wordpress Development</h2>
                                        <h3 data-type="sText" class="sti-item">Understanding visually</h3>
                                        <span data-type="icon" class="sti-icon sti-icon-care sti-item"></span> </a> </li>
                                    <li class="span3 clearfix" data-hovercolor="#49110D"> <a href="#">
                                        <h2 data-type="mText" class="sti-item">Sophisticated Team</h2>
                                        <h3 data-type="sText" class="sti-item">Professionals in action</h3>
                                        <span data-type="icon" class="sti-icon sti-icon-alternative sti-item"></span> </a> </li>
                                    <li class="span3 clearfix" data-hovercolor="#49110D"> <a href="#">
                                        <h2 data-type="mText" class="sti-item">Web Site Design</h2>
                                        <h3 data-type="sText" class="sti-item">Personalized to your needs</h3>
                                        <span data-type="icon" class="sti-icon sti-icon-info sti-item"></span> </a> </li>
                                    <li class="span3 clearfix" data-hovercolor="#49110D"> <a href="#">
                                        <h2 data-type="mText" class="sti-item">Web Development</h2>
                                        <h3 data-type="sText" class="sti-item">Advanced use of technology</h3>
                                        <span data-type="icon" class="sti-icon sti-icon-family sti-item"></span> </a> </li>
                                    <li class="span3 clearfix" data-hovercolor="#49110D" style="margin-left:0;"> <a href="#">
                                        <h2 data-type="mText" class="sti-item">Corporate Identity</h2>
                                        <h3 data-type="sText" class="sti-item">Lorem ipsum dolor</h3>
                                        <span data-type="icon" class="sti-icon sti-icon-globe sti-item"></span> </a> </li>
                                    <li class="span3 clearfix" data-hovercolor="#49110D"> <a href="#">
                                        <h2 data-type="mText" class="sti-item">Brand Creation</h2>
                                        <h3 data-type="sText" class="sti-item">24/7 for you needs</h3>
                                        <span data-type="icon" class="sti-icon sti-icon-brand sti-item"></span> </a> </li>
                                    <li class="span3 clearfix" data-hovercolor="#49110D"> <a href="#">
                                        <h2 data-type="mText" class="sti-item">Corporate Identity</h2>
                                        <h3 data-type="sText" class="sti-item">Lorem ipsum dolor</h3>
                                        <span data-type="icon" class="sti-icon sti-icon-identity sti-item"></span> </a> </li>
                                    <li class="span3 clearfix" data-hovercolor="#49110D"> <a href="#">
                                        <h2 data-type="mText" class="sti-item">Web Development</h2>
                                        <h3 data-type="sText" class="sti-item">Advanced use of technology</h3>
                                        <span data-type="icon" class="sti-icon sti-icon-family sti-item"></span> </a> </li>
                                </ul>
                    </div>
                        </div>
                <div class="container clearfix TitleSection">
                            <header class="page-head">
                        <h1>We Make Our <small>// Clients Happy</small></h1>
                    </header>
                        </div>
                <div class="container clearfix">
                            <div class="span12">
                        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean nibh erat,
                                    sagittis sit amet congue at, aliquam eu libero. Integer molestie, turpis
                                    vel ultrices facilisis, nisi mauris sollicitudin mauris.</p>
                    </div>
                            <div class="row-fluid">
                        <div class="span12">
                                    <div class="slider-wrapper">
                                <div id="slider">
                                            <div class="slide1"> <img src="images/1.jpg" alt="" /> </div>
                                            <div class="slide2"> <img src="images/2.jpg" alt="" /> </div>
                                            <div class="slide3"> <img src="images/3.jpg" alt="" /> </div>
                                            <div class="slide4"> <img src="images/4.jpg" alt="" /> </div>
                                            <div class="slide5"> <img src="images/5.jpg" alt="" /> </div>
                                            <div class="slide6"> <img src="images/6.jpg" alt="" /> </div>
                                            <div class="slide7"> <img src="images/7.jpg" alt="" /> </div>
                                        </div>
                                <div id="slider-direction-nav"></div>
                                <div id="slider-control-nav"></div>
                            </div>
                                </div>
                    </div>
                            <div class="container clearfix TitleSection">
                        <header class="page-head">
                                    <h1>Pricing <small>// Tables</small></h1>
                                </header>
                    </div>
                            <div class="row-fluid">
                        <div class="span12">
                                    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean nibh erat,
                                sagittis sit amet congue at, aliquam eu libero. Integer molestie, turpis
                                vel ultrices facilisis, nisi mauris sollicitudin mauris.</p>
                                </div>
                    </div>
                            <div class="row-fluid">
                        <div class="span4">
                                    <div class="well PricingTables Standard">
                                <h2>Standard</h2>
                                <h3>$15.99 / month</h3>
                                <p><span class="label">POPULAR</span> </p>
                                <ul>
                                            <li><i class="icon-ok-sign"></i>10 users</li>
                                            <li><i class="icon-ok-sign"></i>5TB of space</li>
                                            <li><i class="icon-ok-sign"></i>About Us</li>
                                            <li><i class="icon-ok-sign"></i>News Archive</li>
                                            <li><i class="icon-ok-sign"></i>Our Service</li>
                                            <li><i class="icon-ok-sign"></i>Our Team</li>
                                            <li><i class="icon-ok-sign"></i>Clients Testimonials</li>
                                            <li><i class="icon-ok-sign"></i>Job Opportunities</li>
                                            <li><i class="icon-ok-sign"></i>F.A.Q</li>
                                        </ul>
                                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
                                <p> <a class="btn btn-large" href="#"><i class="icon-ok icon-white"></i> Select plan</a> </p>
                            </div>
                                </div>
                        <div class="span4">
                                    <div class="well PricingTables Professional">
                                <h2>Professional</h2>
                                <h3>$10.99 / month</h3>
                                <p><span class="label label-success">POPULAR</span> </p>
                                <ul>
                                            <li><i class="icon-ok-sign"></i>10 users</li>
                                            <li><i class="icon-ok-sign"></i>5TB of space</li>
                                            <li><i class="icon-ok-sign"></i>About Us</li>
                                            <li><i class="icon-ok-sign"></i>News Archive</li>
                                            <li><i class="icon-ok-sign"></i>Our Service</li>
                                            <li><i class="icon-ok-sign"></i>Our Team</li>
                                            <li><i class="icon-ok-sign"></i>Clients Testimonials</li>
                                            <li><i class="icon-ok-sign"></i>Job Opportunities</li>
                                            <li><i class="icon-ok-sign"></i>F.A.Q</li>
                                        </ul>
                                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
                                <p> <a class="btn btn-success btn-large" href="#"><i class="icon-ok icon-white"></i> Select plan</a> </p>
                            </div>
                                </div>
                        <div class="span4">
                                    <div class="well PricingTables Premium">
                                <h2>Premium</h2>
                                <h3>$8.99 / month</h3>
                                <p><span class="label label-info">BUDGET</span> </p>
                                <ul>
                                            <li><i class="icon-ok-sign"></i>10 users</li>
                                            <li><i class="icon-ok-sign"></i>5TB of space</li>
                                            <li><i class="icon-ok-sign"></i>About Us</li>
                                            <li><i class="icon-ok-sign"></i>News Archive</li>
                                            <li><i class="icon-ok-sign"></i>Our Service</li>
                                            <li><i class="icon-ok-sign"></i>Our Team</li>
                                            <li><i class="icon-ok-sign"></i>Clients Testimonials</li>
                                            <li><i class="icon-ok-sign"></i>Job Opportunities</li>
                                            <li><i class="icon-ok-sign"></i>F.A.Q</li>
                                        </ul>
                                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
                                <p> <a class="btn btn-large" href="#"><i class="icon-ok icon-white"></i> Select plan</a> </p>
                            </div>
                                </div>
                    </div>
                        </div>
            </div>
                </div>
        <!--#Services-->
        <div id="second" class="Parallax">
                    <div class="ParallaxText">
                <h2>WORD <span>OF</span> THE <span>WISE</span></h2>
                <div class="clearfix"></div>
                <blockquote>A man must be big enough to admit his mistakes, smart enough to profit
                            from them, and strong enough to correct them.</blockquote>
                <div class="clearfix"></div>
                <p>- Jason Adams -</p>
            </div>
                </div>
        <!--#second-->
        <div id="Portfolio">
                    <div class="container-fluid clearfix Portfolio">
                <div class="container clearfix TitleSection">
                            <header class="page-head">
                        <h1>Our Portfolio <small>// Lorem ipsum dolor</small></h1>
                    </header>
                        </div>
                <div class="container clearfix">
                            <div class="row-fluid">
                        <div class="span12">
                                    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean nibh erat,
                                sagittis sit amet congue at, aliquam eu libero. Integer molestie, turpis
                                vel ultrices facilisis, nisi mauris sollicitudin mauris.</p>
                                </div>
                    </div>
                        </div>
                <div class="container clearfix">
                            <div class="row-fluid">
                        <div class="span12"> 
                                    
                                    <!-- Portfolio 4 Column start -->
                                    <div class="image_grid portfolio_4col lightbox">
                                <ul id="list" class="portfolio_list da-thumbs clearfix">
                                            <li class="span3">
                                        <div> <img src="images/portfolio/s/portfolio1.jpg" alt="img">
                                                    <article class="da-animate da-slideFromRight" style="display: block;">
                                                <h3>Lorem ipsum dolor</h3>
                                                <em>Lorem ipsum </em> <span class="link_post"><a href="#"></a></span> <span class="zoom"><a href="images/portfolio/big/portfolio1.jpg"></a> </span> </article>
                                                </div>
                                    </li>
                                            <li class="span3"> <img src="images/portfolio/s/portfolio2.jpg" alt="img">
                                        <article class="da-animate da-slideFromTop" style="display: block;">
                                                    <h3>Lorem ipsum dolor</h3>
                                                    <em>Lorem ipsum </em> <span class="link_post"><a href="#"></a></span> <span class="zoom"><a href="images/portfolio/big/portfolio2.jpg"></a></span> </article>
                                    </li>
                                            <li class="span3"> <img src="images/portfolio/s/portfolio3.jpg" alt="img">
                                        <article class="da-animate da-slideFromLeft" style="display: block;">
                                                    <h3>Lorem ipsum dolor</h3>
                                                    <em>Lorem ipsum </em> <span class="link_post"><a href="#"></a></span> <span class="zoom"><a href="images/portfolio/big/portfolio3.jpg"></a></span> </article>
                                    </li>
                                            <li class="span3"> <img src="images/portfolio/s/portfolio4.jpg" alt="img">
                                        <article class="da-animate da-slideFromRight" style="display: block;">
                                                    <h3>Lorem ipsum dolor</h3>
                                                    <em>Lorem ipsum </em> <span class="link_post"><a href="#"></a></span> <span class="zoom"><a href="images/portfolio/big/portfolio4.jpg"></a></span> </article>
                                    </li>
                                            <li class="span3" style="margin-left:0"> <img src="images/portfolio/s/portfolio5.jpg" alt="img">
                                        <article class="da-animate da-slideFromRight" style="display: block;">
                                                    <h3>Lorem ipsum dolor</h3>
                                                    <em>Lorem ipsum </em> <span class="link_post"><a href="#"></a></span> <span class="zoom"><a href="images/portfolio/big/portfolio5.jpg"></a></span> </article>
                                    </li>
                                            <li class="span3"> <img src="images/portfolio/s/portfolio6.jpg" alt="img">
                                        <article class="da-animate da-slideFromTop" style="display: block;">
                                                    <h3>Lorem ipsum dolor</h3>
                                                    <em>Lorem ipsum </em> <span class="link_post"><a href="#"></a></span> <span class="zoom"><a href="images/portfolio/big/portfolio6.jpg"></a></span> </article>
                                    </li>
                                            <li class="span3"> <img src="images/portfolio/s/portfolio7.jpg" alt="img">
                                        <article class="da-animate da-slideFromLeft" style="display: block;">
                                                    <h3>Lorem ipsum dolor</h3>
                                                    <em>Lorem ipsum </em> <span class="link_post"><a href="#"></a></span> <span class="zoom"><a href="images/portfolio/big/portfolio7.jpg"></a></span> </article>
                                    </li>
                                            <li class="span3"> <img src="images/portfolio/s/portfolio8.jpg" alt="img">
                                        <article class="da-animate da-slideFromRight" style="display: block;">
                                                    <h3>Lorem ipsum dolor</h3>
                                                    <em>Lorem ipsum </em> <span class="link_post"><a href="#"></a></span> <span class="zoom"><a href="images/portfolio/big/portfolio8.jpg"></a></span> </article>
                                    </li>
                                            <li class="span3" style="margin-left:0"> <img src="images/portfolio/s/portfolio9.jpg" alt="img">
                                        <article class="da-animate da-slideFromRight" style="display: block;">
                                                    <h3>Lorem ipsum dolor</h3>
                                                    <em>Lorem ipsum </em> <span class="link_post"><a href="#"></a></span> <span class="zoom"><a href="images/portfolio/big/portfolio9.jpg"></a></span> </article>
                                    </li>
                                            <li class="span3"> <img src="images/portfolio/s/portfolio10.jpg" alt="img">
                                        <article class="da-animate da-slideFromTop" style="display: block;">
                                                    <h3>Lorem ipsum dolor</h3>
                                                    <em>Lorem ipsum </em> <span class="link_post"><a href="#"></a></span> <span class="zoom"><a href="images/portfolio/big/portfolio10.jpg"></a></span> </article>
                                    </li>
                                            <li class="span3"> <img src="images/portfolio/s/portfolio11.jpg" alt="img">
                                        <article class="da-animate da-slideFromLeft" style="display: block;">
                                                    <h3>Lorem ipsum dolor</h3>
                                                    <em>Lorem ipsum </em> <span class="link_post"><a href="#"></a></span> <span class="zoom"><a href="images/portfolio/big/portfolio11.jpg"></a></span> </article>
                                    </li>
                                            <li class="span3"> <img src="images/portfolio/s/portfolio12.jpg" alt="img">
                                        <article class="da-animate da-slideFromRight" style="display: block;">
                                                    <h3>Lorem ipsum dolor</h3>
                                                    <em>Lorem ipsum </em> <span class="link_post"><a href="#"></a></span> <span class="zoom"><a href="images/portfolio/big/portfolio12.jpg"></a></span> </article>
                                    </li>
                                        </ul>
                            </div>
                                    <!-- Portfolio 4 Column End --> 
                                    
                                </div>
                    </div>
                        </div>
            </div>
                </div>
        <!--#Portfolio-->
        <div id="third" class="Parallax">
                    <div class="container clearfix TitleSection">
                <header class="page-head">
                            <h1>Our Clients </h1>
                        </header>
                <div class="row-fluid">
                            <div class="span12"> 
                        
                        <!-- Elastislide Carousel -->
                        <div id="carousel" class="clients-wrapper">
                                    <div class="client-carousel">
                                <ul>
                                            <li><a href="#"><img src="images/client/1.png" alt="image01" /></a></li>
                                            <li><a href="#"><img src="images/client/2.png" alt="image02" /></a></li>
                                            <li><a href="#"><img src="images/client/3.png" alt="image03" /></a></li>
                                            <li><a href="#"><img src="images/client/4.png" alt="image04" /></a></li>
                                            <li><a href="#"><img src="images/client/5.png" alt="image05" /></a></li>
                                            <li><a href="#"><img src="images/client/6.png" alt="image06" /></a></li>
                                            <li><a href="#"><img src="images/client/7.png" alt="image07" /></a></li>
                                            <li><a href="#"><img src="images/client/8.png" alt="image08" /></a></li>
                                            <li><a href="#"><img src="images/client/9.png" alt="image09" /></a></li>
                                            <li><a href="#"><img src="images/client/10.png" alt="image10" /></a></li>
                                            <li><a href="#"><img src="images/client/11.png" alt="image11" /></a></li>
                                            <li><a href="#"><img src="images/client/12.png" alt="image12" /></a></li>
                                            <li><a href="#"><img src="images/client/13.png" alt="image13" /></a></li>
                                            <li><a href="#"><img src="images/client/14.png" alt="image14" /></a></li>
                                            <li><a href="#"><img src="images/client/15.png" alt="image15" /></a></li>
                                            <li><a href="#"><img src="images/client/16.png" alt="image16" /></a></li>
                                            <li><a href="#"><img src="images/client/17.png" alt="image17" /></a></li>
                                            <li><a href="#"><img src="images/client/18.png" alt="image18" /></a></li>
                                            <li><a href="#"><img src="images/client/19.png" alt="image19" /></a></li>
                                            <li><a href="#"><img src="images/client/20.png" alt="image20" /></a></li>
                                            <li><a href="#"><img src="images/client/21.png" alt="image21" /></a></li>
                                            <li><a href="#"><img src="images/client/22.png" alt="image22" /></a></li>
                                            <li><a href="#"><img src="images/client/23.png" alt="image23" /></a></li>
                                            <li><a href="#"><img src="images/client/24.png" alt="image24" /></a></li>
                                            <li><a href="#"><img src="images/client/25.png" alt="image25" /></a></li>
                                        </ul>
                            </div>
                                </div>
                        <!-- End Elastislide Carousel --> 
                        
                    </div>
                        </div>
            </div>
                </div>
        <!--#third-->
        <div id="LargeSlider">
                    <div class="container-fluid clearfix LargeSlider">
                <div class="container clearfix TitleSection">
                            <header class="page-head">
                        <h1>Gallery <small>// Lorem ipsum dolor</small></h1>
                    </header>
                        </div>
                <div class="container clearfix">
                            <div class="row-fluid">
                        <div class="span12">
                                    <div id="rg-gallery" class="rg-gallery">
                                <div class="rg-thumbs"> 
                                            <!-- Elastislide Carousel Thumbnail Viewer -->
                                            <div class="es-carousel-wrapper">
                                        <div class="es-nav"> <span class="es-nav-prev">Previous</span> <span class="es-nav-next">Next</span> </div>
                                        <div class="es-carousel">
                                                    <ul>
                                                <li><a href="#"><img src="images/gallery_img/thumbs/1.jpg" data-large="images/gallery_img/1.jpg" alt="image01" data-description="From off a hill whose concave womb reworded" /></a></li>
                                                <li><a href="#"><img src="images/gallery_img/thumbs/2.jpg" data-large="images/gallery_img/2.jpg" alt="image02" data-description="A plaintful story from a sistering vale" /></a></li>
                                                <li><a href="#"><img src="images/gallery_img/thumbs/3.jpg" data-large="images/gallery_img/3.jpg" alt="image03" data-description="A plaintful story from a sistering vale" /></a></li>
                                                <li><a href="#"><img src="images/gallery_img/thumbs/4.jpg" data-large="images/gallery_img/4.jpg" alt="image04" data-description="My spirits to attend this double voice accorded" /></a></li>
                                                <li><a href="#"><img src="images/gallery_img/thumbs/5.jpg" data-large="images/gallery_img/5.jpg" alt="image05" data-description="And down I laid to list the sad-tuned tale" /></a></li>
                                                <li><a href="#"><img src="images/gallery_img/thumbs/6.jpg" data-large="images/gallery_img/6.jpg" alt="image06" data-description="Ere long espied a fickle maid full pale" /></a></li>
                                                <li><a href="#"><img src="images/gallery_img/thumbs/7.jpg" data-large="images/gallery_img/7.jpg" alt="image07" data-description="Tearing of papers, breaking rings a-twain" /></a></li>
                                                <li><a href="#"><img src="images/gallery_img/thumbs/8.jpg" data-large="images/gallery_img/8.jpg" alt="image08" data-description="Storming her world with sorrow's wind and rain" /></a></li>
                                                <li><a href="#"><img src="images/gallery_img/thumbs/9.jpg" data-large="images/gallery_img/9.jpg" alt="image09" data-description="Upon her head a platted hive of straw" /></a></li>
                                                <li><a href="#"><img src="images/gallery_img/thumbs/10.jpg" data-large="images/gallery_img/10.jpg" alt="image10" data-description="Which fortified her visage from the sun" /></a></li>
                                                <li><a href="#"><img src="images/gallery_img/thumbs/11.jpg" data-large="images/gallery_img/11.jpg" alt="image11" data-description="Whereon the thought might think sometime it saw" /></a></li>
                                                <li><a href="#"><img src="images/gallery_img/thumbs/12.jpg" data-large="images/gallery_img/12.jpg" alt="image12" data-description="The carcass of beauty spent and done" /></a></li>
                                                <li><a href="#"><img src="images/gallery_img/thumbs/13.jpg" data-large="images/gallery_img/13.jpg" alt="image13" data-description="Time had not scythed all that youth begun" /></a></li>
                                                <li><a href="#"><img src="images/gallery_img/thumbs/14.jpg" data-large="images/gallery_img/14.jpg" alt="image14" data-description="Nor youth all quit; but, spite of heaven's fell rage" /></a></li>
                                                <li><a href="#"><img src="images/gallery_img/thumbs/15.jpg" data-large="images/gallery_img/15.jpg" alt="image15" data-description="Some beauty peep'd through lattice of sear'd age" /></a></li>
                                                <li><a href="#"><img src="images/gallery_img/thumbs/16.jpg" data-large="images/gallery_img/16.jpg" alt="image16" data-description="Oft did she heave her napkin to her eyne" /></a></li>
                                                <li><a href="#"><img src="images/gallery_img/thumbs/17.jpg" data-large="images/gallery_img/17.jpg" alt="image17" data-description="Which on it had conceited characters" /></a></li>
                                                <li><a href="#"><img src="images/gallery_img/thumbs/18.jpg" data-large="images/gallery_img/18.jpg" alt="image18" data-description="Laundering the silken figures in the brine" /></a></li>
                                                <li><a href="#"><img src="images/gallery_img/thumbs/19.jpg" data-large="images/gallery_img/19.jpg" alt="image19" data-description="That season'd woe had pelleted in tears" /></a></li>
                                                <li><a href="#"><img src="images/gallery_img/thumbs/20.jpg" data-large="images/gallery_img/20.jpg" alt="image20" data-description="And often reading what contents it bears" /></a></li>
                                                <li><a href="#"><img src="images/gallery_img/thumbs/21.jpg" data-large="images/gallery_img/21.jpg" alt="image21" data-description="As often shrieking undistinguish'd woe" /></a></li>
                                                <li><a href="#"><img src="images/gallery_img/thumbs/22.jpg" data-large="images/gallery_img/22.jpg" alt="image22" data-description="In clamours of all size, both high and low" /></a></li>
                                                <li><a href="#"><img src="images/gallery_img/thumbs/23.jpg" data-large="images/gallery_img/23.jpg" alt="image23" data-description="Sometimes her levell'd eyes their carriage ride" /></a></li>
                                                <li><a href="#"><img src="images/gallery_img/thumbs/24.jpg" data-large="images/gallery_img/24.jpg" alt="image24" data-description="As they did battery to the spheres intend" /></a></li>
                                            </ul>
                                                </div>
                                    </div>
                                            <!-- End Elastislide Carousel Thumbnail Viewer --> 
                                        </div>
                                <!-- rg-thumbs --> 
                            </div>
                                </div>
                    </div>
                        </div>
            </div>
                </div>
        <!--#LargeSlider-->
        <div id="fourth" class="Parallax">
                    <div class="container clearfix TitleSection">
                <header class="page-head">
                            <h1>Twitter Feeds </h1>
                        </header>
                <div id="ticker" class="query span8"></div>
            </div>
                </div>
        <!--#fourth-->
        <div id="Contact">
                    <div class="container-fluid clearfix Contact">
                <div class="container clearfix TitleSection">
                            <header class="page-head">
                        <h1>CONTACT Us <small>// LET'S HAVE A DISCUSSION</small></h1>
                    </header>
                        </div>
                <div class="container clearfix">
                            <div class="container">
                        <div class="span6">
                                    <h2>Get In Touch With Us</h2>
                                    <div class="well GetInTouch">
                                <form class="form-horizontal"
                                        id="registerHere" method='post' action='MAILTO:someone@example.com'>
                                            <fieldset>
                                        <div class="control-group">
                                                    <label class="control-label" for="Name">Name</label>
                                                    <div class="controls">
                                                <input type="text" class="span12" id="user_name" name="user_name">
                                            </div>
                                                </div>
                                        <div class="control-group">
                                                    <label class="control-label" for="Email">Email</label>
                                                    <div class="controls">
                                                <input type="text" class="span12" id="user_email" name="user_email">
                                            </div>
                                                </div>
                                        <div class="control-group">
                                                    <label class="control-label" for="Subject">Subject</label>
                                                    <div class="controls">
                                                <input type="text" class="span12" id="pwd" name="pwd">
                                            </div>
                                                </div>
                                        <div class="control-group">
                                                    <label class="control-label" for="YourMessage">Your Message</label>
                                                    <div class="controls">
                                                <textarea class="span12" id="cpwd" name="cpwd"></textarea>
                                            </div>
                                                </div>
                                        <div class="control-group">
                                                    <label class="control-label" for="input01"></label>
                                                    <div class="controls">
                                                <button type="submit" class="btn" title="Send Message">Send Message</button>
                                            </div>
                                                </div>
                                    </fieldset>
                                        </form>
                            </div>
                                </div>
                        <div class="span6">
                                    <h2>Contact Info</h2>
                                    <div class="well" style="padding-bottom:81px;">
                                <div class="span12">
                                            <p style="margin:20px;">Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo
                                        ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis
                                        dis parturient montes. <img src="images/contact.jpg" alt="contact img" style="margin:30px 0;border: 3px solid #F96E5B;"> </p>
                                        </div>
                                <hr>
                                <div class="contact-info">
                                            <ul>
                                        <li> <i class="icon-globe"></i>Lorem ipsum dolor</li>
                                        <li> <i class="icon-bullhorn"></i>+(20) (0) 491.27.29</li>
                                        <li> <i class="icon-envelope"></i> <a href="mailto:ahmedeabbas@yahoo.com">Some@yahoo.com</a> </li>
                                        <li> <i class="icon-map-marker"></i>Lorem ipsum dolor
                                                    consectetuer adipiscing elit.</li>
                                    </ul>
                                        </div>
                            </div>
                                </div>
                    </div>
                        </div>
                <div class="container-fluid">
                            <div class="span12" style="margin-top:20px">
                        <iframe width="100%" height="374" frameborder="0" scrolling="no" marginheight="0"
                                    marginwidth="0" src="https://maps.google.com.eg/maps?f=q&amp;source=s_q&amp;hl=ar&amp;geocode=&amp;q=%D8%B1%D8%B4%D8%AF%D9%8A,+%D8%A7%D9%84%D8%A5%D8%B3%D9%83%D9%86%D8%AF%D8%B1%D9%8A%D8%A9&amp;aq=1&amp;oq=%D8%B1%D8%B4%D8%AF%D9%89+%D8%A7%D9%84%D8%A7%D8%B3%D9%83&amp;sll=26.9061,30.876198&amp;sspn=8.889374,21.643066&amp;ie=UTF8&amp;hq=&amp;hnear=%D8%B1%D8%B4%D8%AF%D9%8A&amp;t=m&amp;ll=31.239591,29.949589&amp;spn=0.036693,0.171661&amp;z=14&amp;iwloc=A&amp;output=embed"></iframe>
                        <br />
                    </div>
                        </div>
            </div>
                </div>
        <!--#Contact-->
        <div id="fifth" class="Parallax">
                    <div class="ParallaxText">
                <h2>WORD <span>OF</span> THE <span>WISE</span></h2>
                <div class="clearfix"></div>
                <blockquote>A man must be big enough to admit his mistakes, smart enough to profit
                            from them, and strong enough to correct them.</blockquote>
                <div class="clearfix"></div>
                <p>- Jason Adams -</p>
            </div>
                </div>
        <!--#fifth-->
        <div class="container-fluid Footer">
                    <div class="container">
                <footer>
                            <ul class="Social">
                        <li> <a rel="tooltip" class="Rss" href="#" data-placement="top" data-original-title="RSS">Rss</a> </li>
                        <li> <a class="Facebook" rel="tooltip" href="#" data-original-title="Facebook">Facebook</a> </li>
                        <li> <a class="Twitter" rel="tooltip" href="#" data-original-title="Twitter">Twitter</a> </li>
                        <li> <a class="Dribbble" rel="tooltip" href="#" data-original-title="Dribbble">Dribbble</a> </li>
                        <li> <a class="YouTube" rel="tooltip" href="#" data-original-title="YouTube">YouTube</a> </li>
                        <li> <a class="Behance" rel="tooltip" href="#" data-original-title="Behance">Behance</a> </li>
                        <li> <a class="Deviantart" rel="tooltip" href="#" data-original-title="Deviantart">Deviantart</a> </li>
                        <li> <a class="Wordpress" rel="tooltip" href="#" data-original-title="Wordpress">Wordpress</a> </li>
                        <li> <a class="Picasa" rel="tooltip" href="#" data-original-title="Picasa">Picasa</a> </li>
                    </ul>
                            <a href="#welcome" class="brand">Parallax<span>707</span> </a>
                            <p>All rights reserved. Theme by &copy; <a href="mailto:ahmedeabbas@yahoo.com">Coyier</a> </p>
                        </footer>
            </div>
                </div>
    </div>
            <!-- /Row --> 
        </div>
<!-- /container -->
<div id="toTop">To Top</div>
<!-- Le javascript==================================================--> 
<!-- Placed at the end of the document so the pages load faster --> 
<script src="js/jquery-1.8.2.min.js"></script> 
<script src="js/lean-slider.min.js"></script><!-- Services Slider--> 
<script src="js/jquery.sticky.min.js"></script><!-- sticky Nav--> 
<script src="js/my_script.min.js"></script> 
<script src="js/bootstrap.min.js"></script> 
<script src="js/jquery.easing.1.3.min.js"></script><!-- parallax--> 
<script src="js/modernizr-2.6.1.min.js"></script><!--blur slidShow --> 
<script src="js/jquery.validate.min.js"></script> 
<script src="js/jquery.parallax-1.1.3.min.js"></script><!--  parallax--> 
<script src="js/jquery.localscroll-1.2.7-min.js"></script><!--  parallax--> 
<script type="text/javascript" src="js/jquery.tmpl.min.js"></script><!--grid slidShow --><!-- &Services Gallery--> 
<script type="text/javascript" src="js/jquery.kinetic.min.js"></script><!--grid slidShow --> 
<script type="text/javascript" src="js/jquery.iconmenu.min.js"></script><!--Services  Blocks--> 
<script type="text/javascript" src="js/jquery-hover-effect.min.js"></script><!--PortfolioGallery--> 
<script src="js/lightbox/jquery.classybox.min.js"></script><!--jquery-classybox LightBox--> 
<script src="js/lightbox/jwplayer.js"></script><!--jquery-classybox LightBox--> 
<script src="js/lightbox/jwplayer.html5.js"></script><!--jquery-classybox LightBox--> 
<script type="text/javascript" src="js/jquery.elastislide.min.js"></script><!-- Gallery Section--> 
<script type="text/javascript" src="js/gallery.min.js"></script><!-- Gallery Section--> 
<script type="text/javascript" src="js/jquery.tweet.min.js"></script><!--Tweet ticker--> 

<!--jquery-classybox LightBox--> 
<script>
        \$(".lightbox span.zoom a").ClassyBox({
        widthWindow:  900
        });  
        </script>
</body>
</html>