## INVENTORY MANAGEMENT APPLICATION

- Inventory application helps companies identify which and how much stock to order. It tracks inventory from purchase to the sale of goods. The practice identifies and responds to trends to ensure there's always enough stock to fulfill customer orders and proper warning of a shortage.
- Based on the Inventory Management the general concepts Sell, Buy, Maintaining stocks are covered in this application.
- The Application manages and organize the Data in MYSQL database management with relational tables and achieve normalisation.
- Application holds the MVC-Model/Controll/View pattern to achieve standardized format of building, by this we can achieve a precise structure.
- The overall performance of the application maintains the inventory with ease and be scalable for further features.

##USECASES

- A dashboard to navigate the user between the inventory process without any interruption. DashBoard has a quick review of TotalStocks, Total revenue, Package status like Shipped,Delivered, low Stock counts etc.
- User can add, remove, update a product or Stock, the stocks are maintain in Database.
- Application holds a feature that is an alert in dashboard has lowStockCounts it is very much useful for to track low stock items and user can made purchase for that stocks.  
- Records about salesorder, purchase order are maintain in database for revenue calculation and it is useful in many ways.
- Trading activities like buy, sell are placed in this application,
- Products/Stocks are organized by categories, user can also play with categories. It's helpful for a user to organize a product in a category. So, a user can serach a product by its categories also
- Customer Datas are maintained and organized,.This helps user to track customer details and can add, delete, updata customer data.
- Maintains a admin management for security and accessing purpose.

##WORK_FLOW

-The View package holds the interaction between user and app in package handling,stock handling, records handling
-Model holds the inventory properties like  admin, customer, product.
-Overall, controller mantains the all the business logic of inventory management some classes are -StockController,TradeController,RevenueController,CustomerController etc...

- Application maintains Customer data, Stock data, Purchase data, Selling data , admin and revenue this helps lot for user to track overall Data.

Technology Used
- JAVA 
- MySql
