package store.ojuara.produtoapi.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ModalidadeEnum {

    ACADEMIA_E_FITNESS("Academia e Fitness"),
    AVENTURA_E_ESPORTES_RADICAIS("Aventura e Esportes Radicais"),
    BASQUETE("Basquete"),
    BICICLETA("Bicicleta"),
    CASUAL("Casual"),
    CORRIDA_E_CAMINHADA("Corrida e Caminhada"),
    FUTEBOL("Futebol"),
    LUTAS_E_ARTES_MARCIAIS("Lutas e Artes Marciais"),
    MOTOCICLISMO("Motociclismo"),
    SKATE("Skate"),
    TENNIS("Tennis"),
    VOLEI("Vôlei");

    private final String descricao;
}
