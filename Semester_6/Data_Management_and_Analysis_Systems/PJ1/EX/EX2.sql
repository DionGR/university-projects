checkpoint
dbcc dropcleanbuffers


create index idx_PARTS_ptype_psize_imanufacturer on parts(ptype, psize) include (manufacturer) 
drop index idx_PARTS_ptype_psize_imanufacturer on parts




/* 

create index SUPPLIERS_nationkey on suppliers(nationkey, sname) include(sphone, s_acctbal, s_comment)
create unique index NATIONS_nationkey on nations(regionkey, nationkey) include(nation)

create unique index REGIONS_region_regionkey on regions(region, regionkey)

create index PARTSUPP_supplycost on partsupp(supplycost)

*/


/* 
drop index NATIONS_nationkey on nations
drop index SUPPLIERS_nationkey on suppliers

drop index PARTSUPP_supplycost on partsupp
drop index REGIONS_region_regionkey on regions
*/