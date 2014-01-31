<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="customerHeader.jsp" />

    <div class="container">
      <div class="row">
        <div class="span3">
            <jsp:include page="customerNav.jsp" />
        </div><!--/span-->
        <div class="span9">
          <div class="page-header">
            <h1>Transaction History</h1>
          </div>

          <c:if test="${fn:length(errors) gt 0}">
            <div class="row">
              <div class="span9">
                <div class="alert alert-error">
                  <button type="button" class="close" data-dismiss="alert">&times;</button>
            	  <c:forEach var="error" items="${errors}">
                  <li>${error}</li>
                  </c:forEach>
                </div>
              </div>
            </div>
          </c:if>
           
          <c:if test="${fn:length(messages) gt 0}">
            <div class="row">
              <div class="span9">
                <div class="alert alert-success">
                  <button type="button" class="close" data-dismiss="alert">&times;</button>
           		  <c:forEach var="message" items="${messages}">
                  <li>${message}</li>
                  </c:forEach>
                </div>
              </div>
            </div>
          </c:if>
		  
          <table class="table table-striped" id="historyTable">
            <thead>
              <tr>
                <th>Date</th>
                <th>Operation</th>
                <th>Fund Name</th>
                <th style="text-align:right;">Number of Shares&nbsp;&nbsp;</th>
                <th style="text-align:right;">Share Prices&nbsp;&nbsp;</th>
                <th style="text-align:right;">Dollar Amount&nbsp;&nbsp;</th>
              </tr>
            </thead>
            <tbody>
            
            <c:forEach var="transaction" items="${transactionList}">
            <tr>
              <td>${transaction.stringDate}</td>
              <td>${transaction.operation}</td>
              <td>${transaction.fundName}</td>
              <td style="text-align:right;">${transaction.stringShares}</td>
              <td style="text-align:right;">${transaction.stringUnitPrice}</td>
              <td style="text-align:right;">${transaction.stringAmount}</td>
            </tr>
            </c:forEach>
            
            </tbody>
          </table>
		  
        </div><!--/span-->
      </div><!--/row-->
<script>
       $.extend( true, $.fn.dataTable.defaults, {
    		"sDom": "<'row-fluid'<'span6'l><'span6'f>r>t<'row-fluid'<'span6'i><'span6'p>>",
    		"sPaginationType": "bootstrap",
    		"oLanguage": {
    			"sLengthMenu": "_MENU_ records per page"
    		}
    	} );


    	/* Default class modification */
    	$.extend( $.fn.dataTableExt.oStdClasses, {
    		"sWrapper": "dataTables_wrapper form-inline"
    	} );


    	/* API method to get paging information */
    	$.fn.dataTableExt.oApi.fnPagingInfo = function ( oSettings )
    	{
    		return {
    			"iStart":         oSettings._iDisplayStart,
    			"iEnd":           oSettings.fnDisplayEnd(),
    			"iLength":        oSettings._iDisplayLength,
    			"iTotal":         oSettings.fnRecordsTotal(),
    			"iFilteredTotal": oSettings.fnRecordsDisplay(),
    			"iPage":          oSettings._iDisplayLength === -1 ?
    				0 : Math.ceil( oSettings._iDisplayStart / oSettings._iDisplayLength ),
    			"iTotalPages":    oSettings._iDisplayLength === -1 ?
    				0 : Math.ceil( oSettings.fnRecordsDisplay() / oSettings._iDisplayLength )
    		};
    	};


    	/* Bootstrap style pagination control */
    	$.extend( $.fn.dataTableExt.oPagination, {
    		"bootstrap": {
    			"fnInit": function( oSettings, nPaging, fnDraw ) {
    				var oLang = oSettings.oLanguage.oPaginate;
    				var fnClickHandler = function ( e ) {
    					e.preventDefault();
    					if ( oSettings.oApi._fnPageChange(oSettings, e.data.action) ) {
    						fnDraw( oSettings );
    					}
    				};

    				$(nPaging).addClass('pagination').append(
    					'<ul>'+
    						'<li class="prev disabled"><a href="#">&larr; '+oLang.sPrevious+'</a></li>'+
    						'<li class="next disabled"><a href="#">'+oLang.sNext+' &rarr; </a></li>'+
    					'</ul>'
    				);
    				var els = $('a', nPaging);
    				$(els[0]).bind( 'click.DT', { action: "previous" }, fnClickHandler );
    				$(els[1]).bind( 'click.DT', { action: "next" }, fnClickHandler );
    			},

    			"fnUpdate": function ( oSettings, fnDraw ) {
    				var iListLength = 5;
    				var oPaging = oSettings.oInstance.fnPagingInfo();
    				var an = oSettings.aanFeatures.p;
    				var i, ien, j, sClass, iStart, iEnd, iHalf=Math.floor(iListLength/2);

    				if ( oPaging.iTotalPages < iListLength) {
    					iStart = 1;
    					iEnd = oPaging.iTotalPages;
    				}
    				else if ( oPaging.iPage <= iHalf ) {
    					iStart = 1;
    					iEnd = iListLength;
    				} else if ( oPaging.iPage >= (oPaging.iTotalPages-iHalf) ) {
    					iStart = oPaging.iTotalPages - iListLength + 1;
    					iEnd = oPaging.iTotalPages;
    				} else {
    					iStart = oPaging.iPage - iHalf + 1;
    					iEnd = iStart + iListLength - 1;
    				}

    				for ( i=0, ien=an.length ; i<ien ; i++ ) {
    					// Remove the middle elements
    					$('li:gt(0)', an[i]).filter(':not(:last)').remove();

    					// Add the new list items and their event handlers
    					for ( j=iStart ; j<=iEnd ; j++ ) {
    						sClass = (j==oPaging.iPage+1) ? 'class="active"' : '';
    						$('<li '+sClass+'><a href="#">'+j+'</a></li>')
    							.insertBefore( $('li:last', an[i])[0] )
    							.bind('click', function (e) {
    								e.preventDefault();
    								oSettings._iDisplayStart = (parseInt($('a', this).text(),10)-1) * oPaging.iLength;
    								fnDraw( oSettings );
    							} );
    					}

    					// Add / remove disabled classes from the static elements
    					if ( oPaging.iPage === 0 ) {
    						$('li:first', an[i]).addClass('disabled');
    					} else {
    						$('li:first', an[i]).removeClass('disabled');
    					}

    					if ( oPaging.iPage === oPaging.iTotalPages-1 || oPaging.iTotalPages === 0 ) {
    						$('li:last', an[i]).addClass('disabled');
    					} else {
    						$('li:last', an[i]).removeClass('disabled');
    					}
    				}
    			}
    		}
    	} );


    	/*
    	 * TableTools Bootstrap compatibility
    	 * Required TableTools 2.1+
    	 */
    	if ( $.fn.DataTable.TableTools ) {
    		// Set the classes that TableTools uses to something suitable for Bootstrap
    		$.extend( true, $.fn.DataTable.TableTools.classes, {
    			"container": "DTTT btn-group",
    			"buttons": {
    				"normal": "btn",
    				"disabled": "disabled"
    			},
    			"collection": {
    				"container": "DTTT_dropdown dropdown-menu",
    				"buttons": {
    					"normal": "",
    					"disabled": "disabled"
    				}
    			},
    			"print": {
    				"info": "DTTT_print_info modal"
    			},
    			"select": {
    				"row": "active"
    			}
    		} );

    		// Have the collection use a bootstrap compatible dropdown
    		$.extend( true, $.fn.DataTable.TableTools.DEFAULTS.oTags, {
    			"collection": {
    				"container": "ul",
    				"button": "li",
    				"liner": "a"
    			}
    		} );
    	}


    	/* Table initialisation */
    	$(document).ready(function() {
    		$('#historyTable').dataTable( {
    			"sDom": "<'row'<'span3'l><'span6'f>r>t<'row'<'span3'i><'span6'p>>",
    			"sPaginationType": "bootstrap",
    			"oLanguage": {
    				"sLengthMenu": "_MENU_ records per page"
    			}
    		} );
    	} );
    
    </script>
<jsp:include page="customerFooter.jsp" />