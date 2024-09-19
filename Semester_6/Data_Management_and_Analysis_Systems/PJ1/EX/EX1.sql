checkpoint
dbcc dropcleanbuffers

create index idx_LINEITEM_shipdate_iprice on lineitem(shipdate) include (price)
create index idx_ORDER_custkey_orderdate on orders(orderdate, custkey)
create index idx_CUSTOMER_cname on customers(cname)

drop index idx_LINEITEM_shipdate_iprice on lineitem
drop index idx_ORDER_custkey_orderdate on orders
drop index idx_CUSTOMER_cname on customers