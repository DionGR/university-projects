checkpoint
dbcc dropcleanbuffers

drop index CUSTOMERS_marketsegment on customers
drop index ORDERS_orderdate on orders

create index CUSTOMERS_marketsegment on customers(custkey) include (market_segment)
create index ORDERS_orderdate on orders(orderdate, custkey) include (totalprice)
