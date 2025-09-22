CREATE TABLE consents (
    id BIGSERIAL PRIMARY KEY,
    consent_purpose TEXT,
    consent_version VARCHAR(50) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT unique_consent_purpose_consent_version UNIQUE (consent_purpose, consent_version)
);

CREATE INDEX idx_consents_consent_purpose ON consents(consent_purpose);

CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    full_name VARCHAR(255) NOT NULL,
    cpf VARCHAR(14) UNIQUE NOT NULL,
    whatsapp_number VARCHAR(20) UNIQUE NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_users_cpf ON users (cpf);
CREATE INDEX idx_users_whatsapp_number ON users (whatsapp_number);

CREATE TABLE signatures (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    consent_id BIGINT NOT NULL,
    interest_content VARCHAR(50) NOT NULL,
    notification_frequency VARCHAR(10) NOT NULL,
    data_type VARCHAR(10) NOT NULL,
    consent_status BOOLEAN NOT NULL,
    status VARCHAR(10) NOT NULL DEFAULT 'Active',
    granted_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_user
        FOREIGN KEY(user_id)
        REFERENCES users(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_consent
            FOREIGN KEY(consent_id)
            REFERENCES consents(id)
            ON DELETE CASCADE,

    CONSTRAINT chk_notification_frequency CHECK (notification_frequency IN ('DAILY', 'WEEKLY')),
    CONSTRAINT chk_data_type CHECK (data_type IN ('AUDIO', 'TEXT', 'VIDEO')),
    CONSTRAINT chk_status CHECK (status IN ('ACTIVE', 'INACTIVE')),
    CONSTRAINT chk_interest_content CHECK (interest_content IN ('OBESITY', 'PHYSICAL ACTIVITY', 'PREGNANCY')),
    CONSTRAINT unique_user_interest UNIQUE (user_id, interest_content)
);

CREATE INDEX idx_signatures_user_id ON signatures (user_id);
CREATE INDEX idx_signatures_interest_content ON signatures (interest_content);