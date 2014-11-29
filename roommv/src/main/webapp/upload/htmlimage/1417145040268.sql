alter table WMS_BACK_INDIV add  flag_id   VARCHAR2(32);
alter table WMS_BACK_INDIV add  back_goods_id   VARCHAR2(32);
comment on column WMS_BACK_INDIV.flag_id is '订单传过来的唯一id  标识唯一记录';
comment on column WMS_BACK_INDIV.back_goods_id is '退货商品明细Id';
alter table WMS_BACK_GOODS add  flag_id   VARCHAR2(32);
comment on column WMS_BACK_GOODS.flag_id is '订单系统传过来唯一化记录标志';
alter table wms_repair_goods modify   actual_num  default 0;
alter table wms_exchange_goods modify   nondefective_num  default 0;
alter table wms_exchange_goods modify   defective_num  default 0;
alter table wms_back_goods modify   nondefective_quantity  default 0;
alter table wms_back_goods modify   defective_quantity  default 0;
update wms_back_indiv t set t.back_goods_id=(select o.id from wms_back_goods o where o.sku_code=t.sku_code and o.back_code=t.back_code and o.sku_code is not null)
where exists(select o.id from wms_back_goods o where o.sku_code=t.sku_code and o.back_code=t.back_code and o.sku_code is not null);

CREATE TABLE returnGoods_item (
id varchar(50) NOT NULL ,
delivery_sn  varchar(50)  NOT NULL ,
sku  varchar(20)  DEFAULT NULL ,
sku_id  varchar(20)  DEFAULT NULL
);


update wms_back_goods t set t.flag_id = (select o.id from returnGoods_item o where o.sku_id=t.goods_sid and o.delivery_sn=t.back_code)
where exists(select o.id from returnGoods_item o where o.sku_id=t.goods_sid and o.delivery_sn=t.back_code);


update  wms_back_indiv t set t.flag_id =(select o.id from returnGoods_item o where o.sku=t.sku_code and o.delivery_sn=t.back_code and o.sku  is not null)
where exists(select o.id from returnGoods_item o where o.sku=t.sku_code and o.delivery_sn=t.back_code and o.sku  is not null);

drop table returnGoods_item;
