# REST API – Tietokantaratkaisut

Tämä projekti on Tietokantaratkaisut-kurssin REST-API taustapalvelu.

Sovellus on toteutettu käyttäen:

- Spring Boot
- Spring Web
- Spring Data JPA

# Luokat / Resurssit

- **Customers** – Käyttäjät  
- **Contacts** – Käyttäjien kontaktit  
- **CustomerAddress** – Käyttäjien osoitteet  
- **Orders** – Käyttäjien tilaukset  
- **OrderItems** – Tilauksien tuotteet  
- **Products** – Tuotteet  
- **Suppliers** – Tavaran toimittajat  

# HTTP-metodit

| Metodi | Kuvaus |
|--------|--------|
| **GET** | Hakee resurssin tai resurssit |
| **PUT** | Päivittää olemassa olevan resurssin tiedot |
| **POST** | Luo uuden resurssin |
| **DELETE** | Poistaa resurssin |

---

## Customers

### GET /customers /customers/{id}
Hakee asiakkaat

##### Response
```json
{
  "id": 1,
  "email": "vsmith1@example.com",
  "phone": "001-789-824-7188x591",
  "firstname": "Mikko",
  "lastname": "Carey"
}
```
### PUT /customers/{id}
päivittää asiakkaan tiedot

#### Request
```json
{
  "id": 1,
  "email": "vas@example.com",
  "phone": "001-789-824-7188x591",
  "firstname": "Mikko",
  "lastname": "Carey"
}
```

### POST /customers

#### Request
```json
{
  "email": "vas@example.com",
  "phone": "001-789-824-7188x591",
  "firstname": "Mikko",
  "lastname": "Carey"
}
```

### DELETE /customers/{id}
Poistaa asiakkaan

## Contacts

### GET /contacts /contacts/{id}
Hakee kontakti tiedot

#### Response
```json
{
  "id": 1,
  "email": "hwilliams5002@example.org",
  "reference": "316e8f76f94b5b1dd8372d78fe7f67ea",
  "customer": null
}
```
### PUT /contacts/{id}

#### Request
```json
{
  "id": 1,
  "email": "vasd@example.org",
  "reference": "316e8f76f94b5b1dd8372d78fe7f67ea",
  "customer": null
}
```
### POST /contacts

#### Request
```json
{
  "email": "vasd@example.org",
  "reference": "316e8f76f94b5b1dd8372d78fe7f67ea",
  "customer": null
}
```
### DELETE /contacts/{id}

## CustomerAddress

### GET /customeraddress /customeraddress/{id}

#### Response
```json
{
  "id": 1,
  "city": "Larryview",
  "country": "Guadeloupe",
  "streetAddress": "39650 Harrington Plains Suite 474",
  "postalcode": "95942"
}
```
### PUT /customeraddress/{id}

#### Request
```json
{
  "id": 1,
  "city": "Larryview",
  "country": "Guadeloupe",
  "streetAddress": "39650 Harrington Plains Suite 474",
  "postalcode": "95942"
}
```
### POST /customeraddress

#### Request
```json
{
  "city": "Larryview",
  "country": "Guadeloupe",
  "streetAddress": "39650 Harrington Plains Suite 474",
  "postalcode": "95942",
  "customer": {
    "id": 51
  }
}
```
## Orders

### GET /orders /orders/{id}

#### Response
```json
{
  "id": 1,
  "order_date": "2024-04-03T14:51:08.000+00:00",
  "delivery_date": "2024-04-06T13:16:43.000+00:00",
  "status": "CANCELLED",
  "orderItems": [
      {
          "id": 2,
          "orderId": 1,
          "productId": 551,
          "quantity": 3,
          "unit_price": 611.67
      }
  ]
}
```
### PUT /orders/{id}

#### Request
```json
{
  "customerId": 1,
  "customerAddressId": 2,
  "deliveryDate": "2024-04-06T13:16:43.000+00:00",
  "status": "CANCELLED",
  "orderItems": [
    {
      "productId": 5,
      "quantity": 2,
      "unit_price": 25.00
    },
    {
      "productId": 2,
      "quantity": 1,
      "unit_price": 736.44
    }
  ]
}
```
### POST /orders

#### Request
```json
{
  "customerId": 1,
  "customerAddressId": 2,
  "order_date": "2024-04-03T14:51:08.000+00:00",
  "delivery_date": "2024-04-06T13:16:43.000+00:00",
  "status": "NEW",
  "orderItems": [
    {
      "productId": 1,
      "quantity": 2,
      "unit_price": 611.67
    },
    {
      "productId": 5,
      "quantity": 1,
      "unit_price": 736.44
    }
  ]
}
```
## OrderItems

### GET /orderitems /orderitems/{id}

#### Response
```json
{
  "id": 1,
  "orderId": 1,
  "productId": 426,
  "quantity": 2,
  "unitPrice": 736.44
}
```
### PUT /orderitems/{id}

#### Request
```json
{
  "orderId": 143,
  "productId": 5,
  "quantity": 3,
  "unitPrice": 19.99
}
```
### POST /orderitems

#### Request
```json
{
  "orderId": 14443,
  "productId": 5,
  "quantity": 3,
  "unitPrice": 19.99
}
```
### DELETE /orderitems/{id}

## Products

### GET /products /products/{id}


#### Response
```json
  {
    "id": 1,
    "name": "Product 1",
    "description": "Her fall move current him.",
    "price": 49.51,
    "stock_quantity": 1620
  }
```

### PUT /products/{id}

#### Request
```json
{
  "id": 1000,
  "name": "Product 14",
  "description": "Her fall move current him.",
  "price": 49.51,
  "stock_quantity": 1620
}
```
### POST /products

#### Request
```json
{
  "name": "Product 1",
  "description": "Her fall move current him.",
  "price": 49.51,
  "stock_quantity": 1620
}
```
### DELETE /products/{id}

## Suppliers

### GET /suppliers /suppliers/{id}

#### Response
```json
{
  "name": "Polar Electronics Oy",
  "contact_name": "Mia Manninen",
  "phone": "0401001000",
  "email": "mia.manninen@polarelec.fi",
  "address": [
      {
          "street_address": "Kairatie 5",
          "postal_code": "96400",
          "city": "Rovaniemi",
          "country": "Suomi"
      }
  ]
}
```
### PUT /suppliers/{id}

#### Request
```json
{
  "name": "Polar Electronics Oy",
  "contact_name": "Mia Manninen",
  "phone": "0401001000",
  "email": "mia.manninen@polarelec.fi",
  "address": [
      {
          "street_address": "Kairatie 5",
          "postal_code": "96400",
          "city": "Rovaniemi",
          "country": "Suomi"
      }
  ]  
}
```
### POST /suppliers

#### request
```json
{
  "name": "Polar Electronics Oy",
  "contact_name": "Mia Manninen",
  "phone": "0401001000",
  "email": "mia.manninen@polarelec.fi",
  "address": [
      {
          "street_address": "Kairatie 5",
          "postal_code": "96400",
          "city": "Rovaniemi",
          "country": "Suomi"
      }
  ]  
}
```
## Status Codes

| Koodi | Selitys |
|------|---------|
| 200 | OK – Pyyntö onnistui |
| 201 | Created – Resurssi luotiin |
| 204 | No Content – Resurssi poistettu |
| 400 | Bad Request – Virheelliset tiedot |
| 404 | Not Found – Resurssia ei löytynyt |
| 500 | Internal Server Error – Palvelinvirhe |


---

# Database

<img src="src/files/relationalschema.png", alt="schema", width="300"/>

