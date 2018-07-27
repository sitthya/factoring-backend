-- Creation of table Product
CREATE TABLE public.product
(
    id bigint NOT NULL,
    "productName" text COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT product_pkey PRIMARY KEY (id)
);

-- Creation of table Client
CREATE TABLE public.client
(
    id bigint NOT NULL,
    "companyName" text COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT client_pkey PRIMARY KEY (id)
);

-- Creation of table Debtor
CREATE TABLE public.debtor
(
    id bigint NOT NULL,
    "companyName" text COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT debtor_pkey PRIMARY KEY (id)
);

-- Creation of table Contract
CREATE TABLE public.contract
(
    id bigint NOT NULL references product(id),
    "productId" bigint NOT NULL,
    "clientId" bigint NOT NULL,
    CONSTRAINT contract_pkey PRIMARY KEY (id)
);

-- Creation of table contract X debtors
CREATE TABLE public.contract_debtor_xref
(
    "contractId" bigint references contract(id),
    "debtorId" bigint references debtor(id),
    UNIQUE ("contractId", "debtorId")
);

-- Creation of table Payment
CREATE TABLE public.payment
(
    id bigint NOT NULL,
    reference text COLLATE pg_catalog."default" NOT NULL,
    amount numeric,
    CONSTRAINT payment_pkey PRIMARY KEY (id)
);

-- Creation of table Invoice
CREATE TABLE public.invoice
(
    id bigint NOT NULL,
    reference text COLLATE pg_catalog."default" NOT NULL,
    "clientId" bigint references client(id),
    "debtorId" bigint references debtor(id),
    amount numeric,
    CONSTRAINT invoice_pkey PRIMARY KEY (id)
);

------------------------------------------------------------------------------------------------------------------------

-- Create the sequences
CREATE SEQUENCE public.productId;
CREATE SEQUENCE public.clientId;
CREATE SEQUENCE public.debtorId;
CREATE SEQUENCE public.contractId;
CREATE SEQUENCE public.paymentId;
CREATE SEQUENCE public.invoiceId;

-- Get the next sequence
Create or replace function get_next_sequence(seqName text) returns bigint
	as $$
	begin
		return nextval(seqName);
	end;
	$$ language plpgsql;