checkpoint
dbcc dropcleanbuffers

set statistics io on
set statistics time on

select cname, orders.orderkey, sum(price) as total
from customers, orders, lineitem
where
 customers.custkey = orders.custkey and
 lineitem.orderkey = orders.orderkey and
 orderdate <= '1993-12-31' and
 shipdate between '1994-01-01' and '1994-02-28'
group by cname, orders.orderkey
order by total desc

set statistics time off
set statistics io off