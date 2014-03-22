--
-- PostgreSQL database dump
--

-- Dumped from database version 9.2.2
-- Dumped by pg_dump version 9.2.0
-- Started on 2014-03-18 22:38:49

SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 2012 (class 1262 OID 24999)
-- Name: dac; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE dac WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Portuguese_Brazil.1252' LC_CTYPE = 'Portuguese_Brazil.1252';


ALTER DATABASE dac OWNER TO postgres;

\connect dac

SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 185 (class 3079 OID 11727)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2015 (class 0 OID 0)
-- Dependencies: 185
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 171 (class 1259 OID 25013)
-- Name: pessoa_fisica; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE pessoa_fisica (
    id integer NOT NULL,
    pessoa_id bigint,
    email character varying,
    cpf character varying(14),
    data_nascimento date
);


ALTER TABLE public.pessoa_fisica OWNER TO postgres;

--
-- TOC entry 170 (class 1259 OID 25011)
-- Name: cliente_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE cliente_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.cliente_id_seq OWNER TO postgres;

--
-- TOC entry 2016 (class 0 OID 0)
-- Dependencies: 170
-- Name: cliente_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE cliente_id_seq OWNED BY pessoa_fisica.id;


--
-- TOC entry 177 (class 1259 OID 25081)
-- Name: endereco; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE endereco (
    id integer NOT NULL,
    pessoa_id bigint,
    endereco character varying,
    complemento character varying,
    numero character varying,
    pais character varying,
    estado character varying,
    cidade character varying,
    cep character varying(9)
);


ALTER TABLE public.endereco OWNER TO postgres;

--
-- TOC entry 176 (class 1259 OID 25079)
-- Name: endereco_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE endereco_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.endereco_id_seq OWNER TO postgres;

--
-- TOC entry 2017 (class 0 OID 0)
-- Dependencies: 176
-- Name: endereco_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE endereco_id_seq OWNED BY endereco.id;


--
-- TOC entry 173 (class 1259 OID 25029)
-- Name: pessoa_juridica; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE pessoa_juridica (
    id integer NOT NULL,
    cnpj character varying(18),
    ativo boolean,
    pessoa_id bigint
);


ALTER TABLE public.pessoa_juridica OWNER TO postgres;

--
-- TOC entry 172 (class 1259 OID 25027)
-- Name: fornecedor_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE fornecedor_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.fornecedor_id_seq OWNER TO postgres;

--
-- TOC entry 2018 (class 0 OID 0)
-- Dependencies: 172
-- Name: fornecedor_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE fornecedor_id_seq OWNED BY pessoa_juridica.id;


--
-- TOC entry 181 (class 1259 OID 25113)
-- Name: nota_fiscal; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE nota_fiscal (
    id integer NOT NULL,
    observacao character varying,
    pessoa_id bigint
);


ALTER TABLE public.nota_fiscal OWNER TO postgres;

--
-- TOC entry 180 (class 1259 OID 25111)
-- Name: nota_fiscal_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE nota_fiscal_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.nota_fiscal_id_seq OWNER TO postgres;

--
-- TOC entry 2019 (class 0 OID 0)
-- Dependencies: 180
-- Name: nota_fiscal_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE nota_fiscal_id_seq OWNED BY nota_fiscal.id;


--
-- TOC entry 184 (class 1259 OID 25145)
-- Name: operacao; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE operacao (
    id integer NOT NULL,
    tipo_operacao character varying(10),
    nf_id bigint,
    valor_total double precision
);


ALTER TABLE public.operacao OWNER TO postgres;

--
-- TOC entry 183 (class 1259 OID 25143)
-- Name: operacao_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE operacao_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.operacao_id_seq OWNER TO postgres;

--
-- TOC entry 2020 (class 0 OID 0)
-- Dependencies: 183
-- Name: operacao_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE operacao_id_seq OWNED BY operacao.id;


--
-- TOC entry 169 (class 1259 OID 25002)
-- Name: pessoa; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE pessoa (
    id integer NOT NULL,
    nome character varying
);


ALTER TABLE public.pessoa OWNER TO postgres;

--
-- TOC entry 168 (class 1259 OID 25000)
-- Name: pessoa_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE pessoa_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.pessoa_id_seq OWNER TO postgres;

--
-- TOC entry 2021 (class 0 OID 0)
-- Dependencies: 168
-- Name: pessoa_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE pessoa_id_seq OWNED BY pessoa.id;


--
-- TOC entry 179 (class 1259 OID 25097)
-- Name: produto; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE produto (
    id integer NOT NULL,
    pessoa_juridica_id bigint,
    descricao character varying,
    valor_compra double precision,
    valor_venda double precision
);


ALTER TABLE public.produto OWNER TO postgres;

--
-- TOC entry 178 (class 1259 OID 25095)
-- Name: produto_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE produto_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.produto_id_seq OWNER TO postgres;

--
-- TOC entry 2022 (class 0 OID 0)
-- Dependencies: 178
-- Name: produto_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE produto_id_seq OWNED BY produto.id;


--
-- TOC entry 182 (class 1259 OID 25122)
-- Name: produto_nf; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE produto_nf (
    produto_id bigint NOT NULL,
    nf_id bigint NOT NULL,
    quantidade bigint
);


ALTER TABLE public.produto_nf OWNER TO postgres;

--
-- TOC entry 175 (class 1259 OID 25042)
-- Name: usuario; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE usuario (
    id integer NOT NULL,
    login character varying(20),
    senha character varying(20),
    pessoa_id bigint
);


ALTER TABLE public.usuario OWNER TO postgres;

--
-- TOC entry 174 (class 1259 OID 25040)
-- Name: usuario_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE usuario_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.usuario_id_seq OWNER TO postgres;

--
-- TOC entry 2023 (class 0 OID 0)
-- Dependencies: 174
-- Name: usuario_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE usuario_id_seq OWNED BY usuario.id;


--
-- TOC entry 1971 (class 2604 OID 25084)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY endereco ALTER COLUMN id SET DEFAULT nextval('endereco_id_seq'::regclass);


--
-- TOC entry 1973 (class 2604 OID 25116)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY nota_fiscal ALTER COLUMN id SET DEFAULT nextval('nota_fiscal_id_seq'::regclass);


--
-- TOC entry 1974 (class 2604 OID 25148)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY operacao ALTER COLUMN id SET DEFAULT nextval('operacao_id_seq'::regclass);


--
-- TOC entry 1967 (class 2604 OID 25005)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pessoa ALTER COLUMN id SET DEFAULT nextval('pessoa_id_seq'::regclass);


--
-- TOC entry 1968 (class 2604 OID 25016)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pessoa_fisica ALTER COLUMN id SET DEFAULT nextval('cliente_id_seq'::regclass);


--
-- TOC entry 1969 (class 2604 OID 25032)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pessoa_juridica ALTER COLUMN id SET DEFAULT nextval('fornecedor_id_seq'::regclass);


--
-- TOC entry 1972 (class 2604 OID 25100)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY produto ALTER COLUMN id SET DEFAULT nextval('produto_id_seq'::regclass);


--
-- TOC entry 1970 (class 2604 OID 25045)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY usuario ALTER COLUMN id SET DEFAULT nextval('usuario_id_seq'::regclass);


--
-- TOC entry 1978 (class 2606 OID 25021)
-- Name: cliente_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY pessoa_fisica
    ADD CONSTRAINT cliente_pkey PRIMARY KEY (id);


--
-- TOC entry 1990 (class 2606 OID 25089)
-- Name: endereco_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY endereco
    ADD CONSTRAINT endereco_pkey PRIMARY KEY (id);


--
-- TOC entry 1982 (class 2606 OID 25034)
-- Name: fornecedor_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY pessoa_juridica
    ADD CONSTRAINT fornecedor_pkey PRIMARY KEY (id);


--
-- TOC entry 1994 (class 2606 OID 25121)
-- Name: nota_fiscal_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY nota_fiscal
    ADD CONSTRAINT nota_fiscal_pkey PRIMARY KEY (id);


--
-- TOC entry 1998 (class 2606 OID 25150)
-- Name: operacao_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY operacao
    ADD CONSTRAINT operacao_pkey PRIMARY KEY (id);


--
-- TOC entry 1980 (class 2606 OID 25071)
-- Name: pessoa_fisica_cpf_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY pessoa_fisica
    ADD CONSTRAINT pessoa_fisica_cpf_key UNIQUE (cpf);


--
-- TOC entry 1984 (class 2606 OID 25078)
-- Name: pessoa_juridica_cnpj_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY pessoa_juridica
    ADD CONSTRAINT pessoa_juridica_cnpj_key UNIQUE (cnpj);


--
-- TOC entry 1976 (class 2606 OID 25010)
-- Name: pessoa_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY pessoa
    ADD CONSTRAINT pessoa_pkey PRIMARY KEY (id);


--
-- TOC entry 1996 (class 2606 OID 25126)
-- Name: produto_nf_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY produto_nf
    ADD CONSTRAINT produto_nf_pkey PRIMARY KEY (produto_id, nf_id);


--
-- TOC entry 1992 (class 2606 OID 25105)
-- Name: produto_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY produto
    ADD CONSTRAINT produto_pkey PRIMARY KEY (id);


--
-- TOC entry 1986 (class 2606 OID 25049)
-- Name: usuario_login_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY usuario
    ADD CONSTRAINT usuario_login_key UNIQUE (login);


--
-- TOC entry 1988 (class 2606 OID 25047)
-- Name: usuario_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (id);


--
-- TOC entry 1999 (class 2606 OID 25065)
-- Name: cliente_pessoa; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pessoa_fisica
    ADD CONSTRAINT cliente_pessoa FOREIGN KEY (pessoa_id) REFERENCES pessoa(id);


--
-- TOC entry 2002 (class 2606 OID 25090)
-- Name: endereco_pessoa_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY endereco
    ADD CONSTRAINT endereco_pessoa_id_fkey FOREIGN KEY (pessoa_id) REFERENCES pessoa(id);


--
-- TOC entry 2000 (class 2606 OID 25072)
-- Name: fornecedor_pessoa; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pessoa_juridica
    ADD CONSTRAINT fornecedor_pessoa FOREIGN KEY (pessoa_id) REFERENCES pessoa(id);


--
-- TOC entry 2004 (class 2606 OID 25156)
-- Name: nota_fiscal_pessoa_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY nota_fiscal
    ADD CONSTRAINT nota_fiscal_pessoa_id_fkey FOREIGN KEY (pessoa_id) REFERENCES pessoa(id);


--
-- TOC entry 2007 (class 2606 OID 25151)
-- Name: operacao_nf_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY operacao
    ADD CONSTRAINT operacao_nf_id_fkey FOREIGN KEY (nf_id) REFERENCES nota_fiscal(id);


--
-- TOC entry 2006 (class 2606 OID 25132)
-- Name: produto_nf_nf_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY produto_nf
    ADD CONSTRAINT produto_nf_nf_id_fkey FOREIGN KEY (nf_id) REFERENCES nota_fiscal(id);


--
-- TOC entry 2005 (class 2606 OID 25127)
-- Name: produto_nf_produto_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY produto_nf
    ADD CONSTRAINT produto_nf_produto_id_fkey FOREIGN KEY (produto_id) REFERENCES produto(id);


--
-- TOC entry 2003 (class 2606 OID 25138)
-- Name: produto_pessoa_juridica_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY produto
    ADD CONSTRAINT produto_pessoa_juridica_id_fkey FOREIGN KEY (pessoa_juridica_id) REFERENCES pessoa_juridica(id);


--
-- TOC entry 2001 (class 2606 OID 25050)
-- Name: usuario_pessoa_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY usuario
    ADD CONSTRAINT usuario_pessoa_id_fkey FOREIGN KEY (pessoa_id) REFERENCES pessoa(id);


--
-- TOC entry 2014 (class 0 OID 0)
-- Dependencies: 5
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2014-03-18 22:38:50

--
-- PostgreSQL database dump complete
--

