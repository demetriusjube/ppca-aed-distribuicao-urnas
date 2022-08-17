package br.jus.tse.distribuicao_urnas.controller;

import br.jus.tse.distribuicao_urnas.domain.Simulacao;
import br.jus.tse.distribuicao_urnas.model.RestricaoSimulacaoDTO;
import br.jus.tse.distribuicao_urnas.model.TipoRestricaoEnum;
import br.jus.tse.distribuicao_urnas.repos.SimulacaoRepository;
import br.jus.tse.distribuicao_urnas.service.RestricaoSimulacaoService;
import br.jus.tse.distribuicao_urnas.util.WebUtils;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/restricaoSimulacaos")
public class RestricaoSimulacaoController {

    private final RestricaoSimulacaoService restricaoSimulacaoService;
    private final SimulacaoRepository simulacaoRepository;

    public RestricaoSimulacaoController(final RestricaoSimulacaoService restricaoSimulacaoService,
            final SimulacaoRepository simulacaoRepository) {
        this.restricaoSimulacaoService = restricaoSimulacaoService;
        this.simulacaoRepository = simulacaoRepository;
    }

    @ModelAttribute
    public void prepareContext(final Model model) {
        model.addAttribute("tipoRestricaoValues", TipoRestricaoEnum.values());
        model.addAttribute("restricoesValues", simulacaoRepository.findAll().stream().collect(
                Collectors.toMap(Simulacao::getId, Simulacao::getDescricao)));
    }

    @GetMapping
    public String list(final Model model) {
        model.addAttribute("restricaoSimulacaos", restricaoSimulacaoService.findAll());
        return "restricaoSimulacao/list";
    }

    @GetMapping("/add")
    public String add(
            @ModelAttribute("restricaoSimulacao") final RestricaoSimulacaoDTO restricaoSimulacaoDTO) {
        return "restricaoSimulacao/add";
    }

    @PostMapping("/add")
    public String add(
            @ModelAttribute("restricaoSimulacao") @Valid final RestricaoSimulacaoDTO restricaoSimulacaoDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "restricaoSimulacao/add";
        }
        restricaoSimulacaoService.create(restricaoSimulacaoDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("restricaoSimulacao.create.success"));
        return "redirect:/restricaoSimulacaos";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable final Long id, final Model model) {
        model.addAttribute("restricaoSimulacao", restricaoSimulacaoService.get(id));
        return "restricaoSimulacao/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable final Long id,
            @ModelAttribute("restricaoSimulacao") @Valid final RestricaoSimulacaoDTO restricaoSimulacaoDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "restricaoSimulacao/edit";
        }
        restricaoSimulacaoService.update(id, restricaoSimulacaoDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("restricaoSimulacao.update.success"));
        return "redirect:/restricaoSimulacaos";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable final Long id, final RedirectAttributes redirectAttributes) {
        restricaoSimulacaoService.delete(id);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("restricaoSimulacao.delete.success"));
        return "redirect:/restricaoSimulacaos";
    }

}
