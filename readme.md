# JSP Mock E-commerce

## System Requirements

### Auth

| function | description |
| --- | --- |
| Login | ID + Password |
| Logout | |
| Sign Up | Email, Phone (not mandatory) |
| Rank Up | Server Manager manually update user rank to Merchant |
| | if rank up, email and phone comes mandatory |
| | Not-implement, I'll just assume that a merchant may contact via contact email |
| Redirect | If user has no permission to page, redirect to fallback page |

### Buyer

| function | description |
| --- | --- |
| See Product List | Sort by time by default |
| Add to Cart | |
| Buy | mock -- delay, empty cart, modify products, go home |
| Write review | Login Mandatory |
| See my review list | Login Mandatory -- not implememt 20241121 |

### Seller

| function | description |
| --- | --- |
| **All buyer functions** | |
| See Product List | Sort by time by default |
| Register new product | |  
| See my products |  |
| Edit my products | expect mainly inventory update |

## Diagrams

![Usecase Diagram](/requirement/usecase.jpg)
![Entity Diagram](/requirement/entity.png)

## Challenges
* Applying partial AJAX + REST
  * Applied REST to review system
  * Harmed consistency of controller implementation -- low readability.
* Passing VO, 