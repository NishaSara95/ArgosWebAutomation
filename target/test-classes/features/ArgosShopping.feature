@regression @sanity

Feature: Shopping at Argos 

Scenario Outline: Verify Search page returns item user searched for and product added to trolley 
  Given User navigates to Argos website
  When User searches for a product "<productCategory>"
 	Then User verifies the search result page contains "<productCategory>"
 	And User adds the product to the trolley "<productname>"
 	Then User validates the product is in the trolley
 	And User increase the quantity by "<quantity>"
  Then User validate the subtotal with unit price and "<quantity>"
  
Examples:

 |productCategory|productname|quantity|
 |Washing Machine|samsung|2|
