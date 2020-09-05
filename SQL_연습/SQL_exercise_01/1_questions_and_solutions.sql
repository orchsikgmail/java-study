-- 1.13 Select the average price of each manufacturer's products, showing the manufacturer's name.
select name, avgPrice 
from manufacturers m
join (
    select avg(price) avgPrice, manufacturer
    from products 
    group by code, manufacturer
) sb
on m.code = sb.manufacturer;

-- 1.14 Select the names of manufacturer whose products have an average price larger than or equal to $150.
select m.name, avg(price)
from manufacturers m join products p
on m.code = p.manufacturer
group by m.name
having avg(price)>=150;

-- 1.15 Select the name and price of the cheapest product.
select name, price 
from products 
where price = (
    select min(price) from products
);

-- 1.16 Select the name of each manufacturer along with the name and price of its most expensive product.
select m.name, price
from manufacturers m join (
    select * from products
    where price in (
        select max(price) price from products
        group by manufacturer
    )
) j
on m.code = j.manufacturer ;

/* With join ~ on ~  and */
SELECT A.Name, A.Price, F.Name
FROM Products A JOIN Manufacturers F
ON A.Manufacturer = F.Code
 AND A.Price =
 (
   SELECT MAX(A.Price)
	 FROM Products A
	 WHERE A.Manufacturer = F.Code
 );
 

---------------------------------------------------


-- 1.1 Select the names of all the products in the store.
select name from products;

-- 1.2 Select the names and the prices of all the products in the store.
select name, price from products;

-- 1.3 Select the name of the products with a price less than or equal to $200.
select name from products where price <=200;

-- 1.4 Select all the products with a price between $60 and $120.
select * from products where price between 60 and 200;

-- 1.5 Select the name and price in cents (i.e., the price must be multiplied by 100).
select name, concat(price*100, ' cents') from products;

-- 1.6 Compute the average price of all the products.
select avg(price) from products;
select sum(price)/count(price) from products;

-- 1.7 Compute the average price of all products with manufacturer code equal to 2.
select avg(price)
from products
where manufacturer = 2;

-- 1.8 Compute the number of products with a price larger than or equal to $180.
select count(code)
from products
where price >= 180;

-- 1.9 Select the name and price of all products with a price larger than or equal to $180, and sort first by price (in descending order), and then by name (in ascending order).
select name, price
from products
where price >= 180
order by price desc, name asc;

-- 1.10 Select all the data from the products, including all the data for each product's manufacturer.
select *
from products p
join manufacturers m
on p.manufacturer = m.code;

-- 1.11 Select the product name, price, and manufacturer name of all the products.
select p.name, p.price, m.name
from products p
join manufacturers m 
on p.manufacturer = m.code;

-- 1.12 Select the average price of each manufacturer's products, showing only the manufacturer's code.
select avg(price), manufacturer
from products
group by manufacturer;

-- 1.17 Add a new product: Loudspeakers, $70, manufacturer 2.
insert into products (code, name, price, manufacturer) 
values (11, 'Looudspeakers', 70, 2);

-- 1.18 Update the name of product 8 to "Laser Printer".
update products set name='Laser Printer' where code=8;

-- 1.19 Apply a 10% discount to all products.
update products set price=price*0.9;

-- 1.20 Apply a 10% discount to all products with a price larger than or equal to $120.
update products set price=price*0.9 where price>=120;