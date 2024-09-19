checkpoint
dbcc dropcleanbuffers

drop index Q4_idx1 on parts
drop index Q4_idx2 on parts

create index Q4_idx1 on parts(brand) include (partkey, pname)
create index Q4_idx2 on parts(manufacturer) include (partkey, pname)

-- drop index PARTS_brand on parts
-- create index PARTS_brand on parts(brand) include (pname, manufacturer)