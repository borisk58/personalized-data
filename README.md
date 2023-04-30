# Personalized Data API
API service that provides shoppers personalized information to eCommerce servers

## API endpoints
### Interface with data team (internal)
Receiving shoppers’ personalized information and product metadata from our data team and storing it 
in a specified database.
<p> POST "/shoppers"
<p> Request body - a JSON with shopper personalized product list

<p> POST "/products"
<p> Request body - a JSON with products metadata


### Interface with eCommerce (external)
Provide fast read operation for the shoppers’ personalized information.

GET "/shoppers"
<p> Response body is the shoppers personalized info by product
<p> Parameters:
<ul>
<li>Product ID - String, required</li>
<li>Limit - Integer, optional, default = 10, max = 1000</li>
</ul>

<p> GET "/products"
<p> Response body is the list of the products by shopper
<p> Parameters:
<ul>
<li>Shopper ID - String, required
<li>Category - String, optional
<li>Brand - String, optional
<li>Limit - Integer, optional, default = 10, max = 100
</ul>