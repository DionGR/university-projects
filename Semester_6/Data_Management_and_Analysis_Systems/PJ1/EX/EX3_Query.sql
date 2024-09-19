checkpoint
dbcc dropcleanbuffers

set statistics io on
set statistics time on

select market_segment, sum(totalprice)
 from customers, orders
 where customers.custkey=orders.custkey and YEAR(orderdate) = 1996
 group by market_segment

 checkpoint
dbcc dropcleanbuffers

SELECT market_segment, SUM(totalprice)
FROM customers
JOIN (
    SELECT custkey, totalprice
    FROM orders
    WHERE orderdate >= '1996-01-01' AND orderdate < '1997-01-01'
) orders ON customers.custkey = orders.custkey
GROUP BY market_segment


set statistics time off
set statistics io off