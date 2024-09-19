checkpoint
dbcc dropcleanbuffers

set statistics io on
set statistics time on

/*select distinct partkey, pname
from parts
WHERE brand='Origin' OR manufacturer='Domkapa'*/

checkpoint
dbcc dropcleanbuffers

select partkey, pname
 from parts
where brand='Origin'
intersect
select partkey, pname
 from parts
where manufacturer='Domkapa'


checkpoint
dbcc dropcleanbuffers
/*select partkey, pname
from parts
where manufacturer='Domkapa'
union
select partkey, pname
from parts
where brand='Origin' */

/*select partkey, pname
 from parts
where brand='Origin' and partkey not in (select partkey
									   from parts
									   where manufacturer='Domkapa' and brand != 'Origin')*/


set statistics time off
set statistics io off