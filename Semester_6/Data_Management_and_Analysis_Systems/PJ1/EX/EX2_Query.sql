checkpoint
dbcc dropcleanbuffers

set statistics io on
set statistics time on

select s_acctbal,sname, nation, parts.partkey, manufacturer, sphone, s_comment
from parts, suppliers,partsupp, nations,regions
where parts.partkey = partsupp.partkey and
 suppliers.suppkey = partsupp.suppkey and
 suppliers.nationkey=nations.nationkey and
 nations.regionkey=regions.regionkey and
 psize = 31 and
 ptype = 'LARGE PLATED TIN' and
 region = 'EUROPE' and
 supplycost = ( select min(supplycost)
				from partsupp, suppliers, nations, regions
				where parts.partkey = partsupp.partkey and
					  suppliers.suppkey = partsupp.suppkey and
					  suppliers.nationkey=nations.nationkey and
					  nations.regionkey=regions.regionkey and
					  region='EUROPE')
order by s_acctbal desc, nation, sname, parts.partkey;

set statistics time off
set statistics io off