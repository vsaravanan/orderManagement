-- 1. Add indexes on filtered/sorted columns
CREATE INDEX idx_trade_date ON TRADES(trade_date);

-- 2. Partition by date range
ALTER TABLE TRADES PARTITION BY RANGE (trade_date) (
    PARTITION p_2023 VALUES LESS THAN ('2024-01-01')
);

-- 3. Rewrite query to use covering index
EXPLAIN ANALYZE SELECT trade_id FROM TRADES
WHERE trade_date > '2024-01-01' AND currency = 'USD';